<jsp:useBean id="paginacao" class="br.com.consorciosdf.intranet.modelo.Paginacao" scope="request"/>

<%
    
    String orderBy = ""; //ordenar por (data, nº de os, equipamento)
    String order = "ASC";
    
    int paginaInicial = 0; //página atual da navegação
    int qtdRegistros = 30; //quantidade de registros por página;
    
    //paginacao por order by
    if ((request.getParameter("orderBy")) != null) {
        orderBy = request.getParameter("orderBy");
        
        if (session.getAttribute("orderBy") == null) {
            session.setAttribute("orderBy", orderBy);
            session.setAttribute("order", "ASC");
        } else {
            String orderBySession = (String) session.getAttribute("orderBy");
            String orderSession = (String) session.getAttribute("order");
            
            if (orderBySession.equals(orderBy)) {
                if (orderSession.equals("ASC")) {
                    session.setAttribute("order", "DESC");
                } else {
                    session.setAttribute("order", "ASC");
                }
            } else {
                session.setAttribute("orderBy", orderBy);
                session.setAttribute("order", "ASC");
            }
        }
    }
    
    //paginacao
    if ((request.getParameter("page")) != null) {
        paginaInicial = Integer.parseInt(request.getParameter("page"));
        paginaInicial--;
    } else {
        if ((request.getParameter("orderBy")) != null) {
            session.removeAttribute("orderBy");
            session.removeAttribute("order");
        }
    }

    if (session != null) {
        if (session.getAttribute("orderBy") != null) {
            orderBy = (String) session.getAttribute("orderBy");
            order = (String) session.getAttribute("order");
        }
    }
    
%>
