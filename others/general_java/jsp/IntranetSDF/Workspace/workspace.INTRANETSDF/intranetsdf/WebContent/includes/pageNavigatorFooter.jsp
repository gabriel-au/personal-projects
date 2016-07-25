<%

            String pageViewNavigator = "";

            int pageFirst = 0;
            int pagePrevious = 0;
            int pageNext = 0;//
            int pageLast = 0;//total de paginas
            int pageNumber = 0;//pagina atual
            int pageShowAfter = 5;//P�ginas a se mostrar ap�s a atual
            int pageShowBefore = 5;//P�ginas a se mostrar antes da atual
            int posAddition = 0;
            int preAddition = 0;
            int pagesBefore = 0;
            int pagesAfter = 0;
            



            if (paginacao != null) {
                
                String parametrosPesquisa = "";
                /*
                 * Carregando os par�mentros do filtro para preencher a pagina��o
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
                 * Fim do carregamento de par�mentros.
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
                 * Se o total de p�ginas anteriores for menor que 6
                 * o total de p�ginas a serem mostradas ap�s a p�gina atual
                 * vai ser = ao total de p�ginas restantes.
                 **/
                if (pageNumber < pageShowBefore + 1) {

                    pageShowBefore = pageNumber;
                    if (pageLast >= 10) {
                        posAddition = 4 - pageShowBefore;
                    }

                }
                /*
                 * Se o total de p�ginas posteriores for menor que 5
                 * o total de p�ginas a serem mostradas ap�s a p�gina atual 
                 * vai ser = ao total de p�ginas restantes.
                 **/
                if (pagesAfter < pageShowAfter) {
                    pageShowAfter = pagesAfter;
                    /*
                     * Se o total de p�ginas for maior que 10
                     * ser�o acrescentadas ao inicio da sequencia de p�ginas mostradas um total de (5 p�ginas - o total de p�ginas mostradas ap�s a atual);
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

                    //P�ginas a se mostrar anteriores a atual

                    for (int i = (pageNumber - pageShowBefore) - preAddition; i < pageNumber - 1; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }


                    //P�ginas a se mostrar ap�s a atual
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
                    pageViewNavigator += " | <a href='?page=" + pageNext + parametrosPesquisa + "'>Pr�xima &gt;</a> | ";
                }
                if (pageNumber + 10 <= paginacao.getPageNavigator()) {
                    int link = pageNumber + 10;
                    pageViewNavigator += " <a href='?page=" + link + parametrosPesquisa + "'>Pr�ximas 10 &gt;</a> | ";
                }
                if (pageNumber < paginacao.getPageNavigator()) {
                    pageViewNavigator += "<a href='?page=" + pageLast + parametrosPesquisa + "'>�ltima &gt;&gt;</a>";
                }
            }

%>