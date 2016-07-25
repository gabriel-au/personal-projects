package br.com.consorciosdf.intranet.modelo;

public class Paginacao {
    
    private int pageNumber = 0; //numero da pagina atual
    private int pageLimit = 0; // numero de registros por pagina
    private int pageInit = 0; //pagina inicial, inicio do ponteiro (query)
    private int resultSize = 0; //quantidade de registros resultantes da query
    private int pageNavigator = 0; //quantidade de paginas que serao exibidas
    private int pageLeft = 0;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getPageInit() {
        return pageInit;
    }

    public void setPageInit(int pageInit) {
        this.pageInit = pageInit;
    }

    public int getResultSize() {
        return resultSize;
    }

    public void setResultSize(int resultSize) {
        this.resultSize = resultSize;
    }

    public int getPageNavigator() {
        return pageNavigator;
    }

    public void setPageNavigator(int pageNavigator) {
        this.pageNavigator = pageNavigator;
    }

    public int getPageLeft() {
        return pageLeft;
    }

    public void setPageLeft(int pageLeft) {
        this.pageLeft = pageLeft;
    }
    
}
