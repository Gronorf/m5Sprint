
package dao;


public interface DAOManager {
    CapacitacionDAO getCapacitacionDAO();
    ClienteDAO getClienteDAO();
    UsuarioDAO getUsuarioDAO();
    AdministrativoDAO getAdministrativoDAO();
    ProfesionalDAO getProfesionalDAO();
}
