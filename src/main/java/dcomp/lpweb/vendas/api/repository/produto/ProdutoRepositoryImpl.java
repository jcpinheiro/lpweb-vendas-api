package dcomp.lpweb.vendas.api.repository.produto;

import dcomp.lpweb.vendas.api.model.Produto;
import dcomp.lpweb.vendas.api.repository.filter.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Produto> filtrar(ProdutoFiltro filtro) {


        // Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cq)
        // com a tipagem do tipo a ser selecionado (Produto)
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        // 1. Select p From Produto p
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class );

        // 2. clausula from e joins
        Root<Produto> produtoRoot = cq.from(Produto.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot  );

        // 4. monta a consulta com as restrições
        cq.select(produtoRoot )
          .where(restricoes )
          .orderBy( cb.asc(produtoRoot.get("nome")) );


        // 5. cria e executa a consula
        return manager.createQuery(cq).getResultList();
    }


    private Predicate[] criaRestricoes(ProdutoFiltro filtro, CriteriaBuilder cBuilder, Root<Produto> produtoRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if ( !StringUtils.isEmpty( filtro.getNome()) ) {
           // where nome like %Computador%
           predicates.add(cBuilder.like(cBuilder.lower(produtoRoot.get("nome")), "%" + filtro.getNome().toLowerCase() + "%" ) );

        }

        if ( Objects.nonNull(filtro.getPrecoDe()) ) {
            predicates.add( cBuilder.ge( produtoRoot.get("precoAtual"), filtro.getPrecoDe() ));
       }

        if( Objects.nonNull( filtro.getPrecoAte()  ) ) {
            predicates.add( cBuilder.le( produtoRoot.get("precoAtual"), filtro.getPrecoAte() ));
        }

        if ( Objects.nonNull( filtro.getAtivo()) ) {
            predicates.add( cBuilder.equal( produtoRoot.get("ativo"), filtro.getAtivo() ));
        }
        if (Objects.nonNull(filtro.getCategoriaId()) ) {

            // antes fazemos o join com categorias
            Path<Integer> categoriaPath = produtoRoot.join("categorias").<Integer>get("id");

            // semelhante a clausula "on" do critério de junção
            predicates.add ( cBuilder.equal(categoriaPath, filtro.getCategoriaId() ) );
        }

       return predicates.toArray(new Predicate[ predicates.size() ] );
    }


    // --------------------------- Com paginação -----------------------------------------

    @Override
    public Page<Produto> filtrar(ProdutoFiltro filtro, Pageable pageable) {
        return null;
    }


}
