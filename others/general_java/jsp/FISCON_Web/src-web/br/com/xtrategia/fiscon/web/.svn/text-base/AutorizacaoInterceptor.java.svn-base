package br.com.xtrategia.fiscon.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class AutorizacaoInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;     
	
    public void init() {}     
    
    public String intercept(ActionInvocation invocation) throws Exception   
    {     
       //SessionMap sessao = (SessionMap) ActionContext.getContext().get(ActionContext.SESSION);
          
    	if(invocation.getAction() instanceof WebAction){
    		WebAction action = (WebAction)invocation.getAction();
    		//verificar se precisa do login e se está logado
    		if(action.isLoggedInUserRequired() && action.getLogado()==null){
    			action.setMensagem("É necessário efetuar a autenticação");
    			return "login";
    		}else if(action.isLoggedInUserRequired() && action.getLogado()!=null){
    			//gravar o log
    			action.gravarLogAcesso(action.getClass().getName()+"."+invocation.getProxy().getMethod());
    		}
    	}
    	
        return invocation.invoke();     
    }     
  
    public void destroy() {}    

}
