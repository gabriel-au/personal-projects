<%

            String pageViewNavigator = "";

            int pageFirst = 0;
            int pagePrevious = 0;
            int pageNext = 0;//
            int pageLast = 0;//total de paginas
            int pageNumber = 0;//pagina atual
            int pageShowAfter = 5;//Páginas a se mostrar após a atual
            int pageShowBefore = 5;//Páginas a se mostrar antes da atual
            int posAddition = 0;
            int preAddition = 0;
            int pagesBefore = 0;
            int pagesAfter = 0;
            



            if (paginacao != null) {
                
                String parametrosPesquisa = "";
                /*
                 * Carregando os parâmentros do filtro para preencher a paginação
                 *
                if (request.getParameter("numOS") != null) {

                    parametrosPesquisa += "&numOS=" + request.getParameter("numOS");
                }
                if (request.getParameter("statusOS") != null) {
                    parametrosPesquisa += "&statusOS=" + request.getParameter("statusOS");

                }
                if (request.getParameter("tipoOS") != null) {
                    parametrosPesquisa += "&tipoOS=" + request.getParameter("tipoOS");

                }
                if (request.getParameter("equipamento") != null) {
                    parametrosPesquisa += "&equipamento=" + request.getParameter("equipamento");

                }
                if (request.getParameter("tecnico") != null) {
                    parametrosPesquisa += "&tecnico=" + request.getParameter("tecnico");

                }
                if (request.getParameter("dt_ini_dia") != null &&
                        request.getParameter("dt_ini_mes") != null &&
                        request.getParameter("dt_ini_ano") != null) {



                    parametrosPesquisa += "&dt_ini_dia=" + request.getParameter("dt_ini_ano");
                    parametrosPesquisa += "&dt_ini_mes=" + request.getParameter("dt_ini_mes");
                    parametrosPesquisa += "&dt_ini_ano=" + request.getParameter("dt_ini_dia");


                }
                if (request.getParameter("dt_fim_dia") != null &&
                        request.getParameter("dt_fim_mes") != null &&
                        request.getParameter("dt_fim_ano") != null) {

                    parametrosPesquisa += "&dt_fim_ano=" + request.getParameter("dt_fim_ano");
                    parametrosPesquisa += "&dt_fim_mes=" + request.getParameter("dt_fim_mes");
                    parametrosPesquisa += "&dt_fim_dia=" + request.getParameter("dt_fim_dia");

                }*/
                /*
                 * Fim do carregamento de parâmentros.
                 **/
                pageNumber = paginacao.getPageNumber();
                pageNumber++;

                pageFirst = 1;
                pagePrevious = 1;
                pageNext = pageNumber + 1;
                pageLast = paginacao.getPageNavigator();
                pagesBefore = pageNumber - 1;
                pagesAfter = pageLast - pageNumber;
                /*
                 * Se o total de páginas anteriores for menor que 6
                 * o total de páginas a serem mostradas após a página atual
                 * vai ser = ao total de páginas restantes.
                 **/
                if (pageNumber < pageShowBefore + 1) {

                    pageShowBefore = pageNumber;
                    if (pageLast >= 10) {
                        posAddition = 4 - pageShowBefore;
                    }

                }
                /*
                 * Se o total de páginas posteriores for menor que 5
                 * o total de páginas a serem mostradas após a página atual 
                 * vai ser = ao total de páginas restantes.
                 **/
                if (pagesAfter < pageShowAfter) {
                    pageShowAfter = pagesAfter;
                    /*
                     * Se o total de páginas for maior que 10
                     * serão acrescentadas ao inicio da sequencia de páginas mostradas um total de (5 páginas - o total de páginas mostradas após a atual);
                     */
                    if (pageLast >= 10) {
                        preAddition = 5 - pageShowAfter;
                    }

                }
                if (pagesAfter < pageShowAfter && pageLast >= 10) {
                    preAddition = 5 - pageShowAfter;

                }
                if (pageNumber < (pageShowBefore + 1) && pageLast >= 10) {
                    posAddition = 4 - pageShowBefore;

                }
                if (pageNumber > 1) {
                    pagePrevious = pageNumber - 1;
                    pageViewNavigator += "<a href='?page=" + pageFirst + parametrosPesquisa + "'>&lt;&lt; Primeira</a> | ";
                }
                if (pageNumber > 10) {
                    int link = pageNumber - 10;
                    pageViewNavigator += "<a href='?page=" + link + parametrosPesquisa + "'>&lt; 10 Anteriores</a> |";
                }
                if (pageNumber > 1) {
                    pagePrevious = pageNumber - 1;
                    pageViewNavigator += " <a href='?page=" + pagePrevious + parametrosPesquisa + "'>&lt; Anterior</a> |";
                }
                if (pageLast >= 10) {

                    //Páginas a se mostrar anteriores a atual

                    for (int i = (pageNumber - pageShowBefore) - preAddition; i < pageNumber - 1; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }


                    //Páginas a se mostrar após a atual
                    for (int i = pageNumber - 1; i < pageNumber + pageShowAfter + posAddition; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }
                } else {
                    
                    for (int i = 0; i < pageLast; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }

                }




                if (pageNumber < paginacao.getPageNavigator()) {
                    pageViewNavigator += " | <a href='?page=" + pageNext + parametrosPesquisa + "'>Próxima &gt;</a> | ";
                }
                if (pageNumber + 10 <= paginacao.getPageNavigator()) {
                    int link = pageNumber + 10;
                    pageViewNavigator += " <a href='?page=" + link + parametrosPesquisa + "'>Próximas 10 &gt;</a> | ";
                }
                if (pageNumber < paginacao.getPageNavigator()) {
                    pageViewNavigator += "<a href='?page=" + pageLast + parametrosPesquisa + "'>Última &gt;&gt;</a>";
                }
            }

%>