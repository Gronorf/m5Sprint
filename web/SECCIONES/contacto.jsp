<%-- 
    Document   : contacto
    Created on : Mar 22, 2023, 6:05:42 PM
    Author     : Leonel Briones Palacios
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- BOOTSTRAP CSS v5.2.3 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <!-- CUSTOM CSS -->
        <link rel="stylesheet" href="/assets/css/styles.css"/>
        <title>Contacto</title>
    </head>

    <body class="container">
        <nav>
            <%@include file="COMPONENTES/navbar.jsp" %>
        </nav>

        <main>

            <h2 class="text-center py-3 my-5">Contacto</h2>

            <c:if test="${not empty mensajeExito}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" id="mensajeExito">
                    <strong>¡Exito!</strong> ${mensajeExito}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="row justify-content-center">
                <form action="SvContacto" method="POST" class="needs-validation col-xs-12 col-sm-11 col-md-9 col-xl-8" novalidate>


                    <div class="form-floating mb-3">
                        <input type="email" name="email" id="email" class="form-control" placeholder="Email" required="true">
                        <label for="email">Email:</label>
                        <div class="invalid-feedback">
                            Por favor ingrese su Email.
                        </div>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="text" name="name" id="nombre" class="form-control" placeholder="Nombre de Contacto" required="true">
                        <label for="nombre">Nombre:</label>
                        <div class="invalid-feedback">
                            Por favor ingrese su Nombre.
                        </div>
                    </div>

                    <div class="form-floating mb-3">
                        <textarea name="comentarios" id="comentarios" cols="30" rows="10" class="form-control" placeholder="Escriba sus comentarios aquí" style="height: 100px; resize:none " required="true"></textarea>
                        <label for="comentarios">Comentarios:</label>
                        <div class="invalid-feedback">
                            Por favor escriba sus comentarios.
                        </div>
                    </div>

                    <div>
                        <input type="submit" value="Enviar" class="btn btn-primary mb-3 w-50">
                    </div>

                </form>
            </div>

        </main>

        <!-- BOOTSTRAP JS v5.2.3 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

        <!-- VALIDATION CSS JS -->
        <script src="assets/js/validationBootstrapCSSJS.js"></script>
    </body>

    <footer class="py-3">
        <%@include file="COMPONENTES/footer.jsp" %>
    </footer>
</html>
