
package mysql;
import conexion.ConexionSingleton;
import dao.AdministrativoDAO;
import dao.CapacitacionDAO;
import dao.ClienteDAO;
import dao.DAOManager;
import dao.ProfesionalDAO;
import dao.UsuarioDAO;
import java.sql.*;


public class MySQLDaoManager implements DAOManager{
    private Connection conn;
    
    private CapacitacionDAO capacitacion = null;
    private ClienteDAO cliente = null;
    private UsuarioDAO usuario = null;
    private AdministrativoDAO administrativo = null;
    private ProfesionalDAO profesional = null;
    
    public MySQLDaoManager(){
        conn = ConexionSingleton.getConexion();
    };

    @Override
    public CapacitacionDAO getCapacitacionDAO() {
        if(capacitacion ==  null){
            capacitacion = new MySQLCapacitacionDAO(conn);
        }
        return capacitacion;
    }

    @Override
    public ClienteDAO getClienteDAO() {
        if(cliente == null){
            cliente = new MySQLClienteDAO(conn);
        }
        return cliente;
    }
    
    /*PROBAR LA CONEXION
    public static void main(String[] args) throws SQLException, DAOException{
        MySQLDaoManager man = new MySQLDaoManager();
        List<Capacitacion> capacitaciones = man.getCapacitacionDAO().obtenerTodos();
        System.out.println(capacitaciones);
    }
    */

    @Override
    public UsuarioDAO getUsuarioDAO() {
        if(usuario == null){
            usuario = new MySQLUsuarioDAO(conn);
        }
        return usuario;
    }

    @Override
    public AdministrativoDAO getAdministrativoDAO() {
        if(administrativo == null){
            administrativo = new MySQLAdministrativoDAO(conn);
        }
        return administrativo;
    }

    @Override
    public ProfesionalDAO getProfesionalDAO() {
        if(profesional == null){
            profesional = new MySQLProfesionalDAO(conn);
        }
        return profesional;
    }
}
