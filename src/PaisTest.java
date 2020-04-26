
import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;


public class PaisTest {
	int id =2;
		Pais pais=new Pais(id,"Alemanha",2345,234);
		Pais copia=new Pais(id,"Alemanha",2345,234);
			

	@Test
	void testCarrega() throws SQLException {
		System.out.println("Consulta");
		
		Pais fix = new Pais("Brasil",1234,5678);
		Pais copia = new Pais();
		copia.carregar(1);
		assertEquals("testa consulta",copia,fix);
	}
	
	@Test	
	void testIncluir() throws SQLException {
		pais.incluir();
		id = pais.getId();
		copia.setId(id);
		assertEquals("Testa inclusao", pais, copia);
	}
	@Test
	
	void testAtualizar() throws SQLException {
		pais.setPopulacao(123456789);
		copia.setPopulacao(123456789);
		pais.atualiza();
		pais.carregar(pais.getId());
		assertEquals("teste atualizacao", pais, copia);
	}
	
	@Test
	void testExcluir() throws SQLException {
		Pais vazio = new Pais();
		pais.excluir();
		assertEquals("teste exclusao", pais, vazio);
		
	}
	
	@Test
	void testmaiorPopulacao() throws SQLException {
		Pais teste = new Pais();
		teste.consultapopulacao();
		teste.maiorpopulacao();
		assertEquals("teste maior populacao", teste.getPopulacao(),2345);
	}
	
	@Test
	void testmenorArea() throws SQLException {
		Pais teste = new Pais();
		teste.consultapopulacao();
		teste.menorArea();
		System.out.println(teste.getArea());	
		assertEquals("teste menor area", teste.getArea(),234);
		
	}


}