package software.JavaEE.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// adiciona o webFilter, e configura para o tipo de situação (REQUEST) e o padrão da url do request (nesse caso
// está especificando qualquer url com .do no final)
@WebFilter(dispatcherTypes={DispatcherType.REQUEST} , urlPatterns="/*")
// implementa a classe FIlter do servlet, para comportamento padrão
public class FiltroAutenticacao implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
             // define o fluxo para os requests
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			// pega a url do request
			String uri = httpRequest.getRequestURI();
			HttpSession sessao = httpRequest.getSession(false);
			
			/* indexOf retorna a posição de uma palavra em uma string, se n tiver nada, retorna -1
			     se tiver login e autenticador.do na url, o chama o filtro 
			     Verifica se a sessão existe 
			     3 verdades */
			if (  sessao!=null || uri.indexOf("Login.html") != -1	|| uri.indexOf("autenticador.do") != -1 ){
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect("Login.html");
			}
	}

	@Override
	public void destroy() {
		
		
	}

}
