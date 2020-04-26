import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {
	int id;
	String nome;
	long populacao;
	double area;
	ArrayList<Pais> lista = new ArrayList<>();
	Connection conexao=null;
	ConexaoDB db = new ConexaoDB();
	
	public Pais() {
		
	}
	public Pais(long populacao) {
		this.populacao = populacao;
	}
	public Pais(int id, String nome, int populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public Pais( String nome, int populacao, double area) {
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getPopulacao() {
		return populacao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}

	public void incluir() throws SQLException {
		Connection conn = ConexaoDB.conectar();
		String sqlInsert = "INSERT INTO pais(nome,populacao,area) VALUES (?,?,?)";
		
		try (PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println(e1.getStackTrace());
			}
		}
	}
	
	public void excluir() throws SQLException {
		Connection conn = db.conectar();
		String sqlDelete = "DELETE FROM pais Where id = ?";
		try (PreparedStatement stm = conn.prepareStatement(sqlDelete);){
			stm.setInt(1, getId());
			stm.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println(e1.getStackTrace());
			}
		}
	}
	
	public void atualiza() throws SQLException {
		Connection conn = db.conectar();
		String sqlUpdate = "UPDATE pais SET populacao= ?, area=? WHERE id=? ";
		try(PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setLong(1, getPopulacao());
			stm.setDouble(2, getArea());
			stm.setInt(3, getId());
			stm.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println(e1.getStackTrace());
			}
		}
	}
	
	public ArrayList<Pais> consultapopulacao () throws SQLException {
		Connection conn = db.conectar();
		String consulta = "select id,nome,populacao,area from pais";
		
		
		try (PreparedStatement stm = conn.prepareStatement(consulta); ResultSet rs = stm.executeQuery();){
			while(rs.next()) {
				Pais p = new Pais();
				p.setId(rs.getInt("id"));
				p.setPopulacao(rs.getLong("populacao"));
				p.setNome(rs.getString("nome"));
				p.setArea(rs.getDouble("area"));
				lista.add(p);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void carregar(int j) throws SQLException {
		String carrega = "SELECT nome,populacao,area FROM pais WHERE id=?";
		ConexaoDB db=new ConexaoDB();
		try(Connection conn = db.conectar(); PreparedStatement stm = conn.prepareStatement(carrega);){
			stm.setInt(1, j);
			try (ResultSet rs = stm.executeQuery();){
				while(rs.next())	{
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void maiorpopulacao() throws SQLException {
		long popula = 0;String nome = null;
		for(Pais populacao : lista) {
			if(popula<populacao.getPopulacao()) {
				popula=populacao.getPopulacao();
				nome = populacao.getNome();
				this.setNome(nome);
				this.setPopulacao(popula);
				
		}
		}
		System.out.println("Pais: "+nome+", Popupacao: "+popula);
	}
	
	public void menorArea()  {
		String nome=null; double area=0;
		
		for(Pais pais : lista) {	
			
			if(area==0 ||area>pais.getArea()) {
				area=pais.getArea(); nome=pais.getNome();
			this.setNome(nome);;
			this.setArea(area);
			}
		}
		System.out.println("Pais: "+nome+", Area: "+area);
	}	
	public void vetorTresPaises() {
		int i;
		for( i = 0; i <= 2; i++) {
			System.out.println("Pais:"+lista.get(i).getNome());
		}
	}
	
	
}