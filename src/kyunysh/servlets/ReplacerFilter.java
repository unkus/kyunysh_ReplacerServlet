package kyunysh.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class ReplacerFilter
 */
@WebFilter(urlPatterns = { "/ReplacerServlet" }, initParams = {
		@WebInitParam(name = "StopList", value = "один,три,пять,семь,девять") })
public class ReplacerFilter implements Filter {

	private final List<String> itsStopList = new LinkedList<String>();

	/**
	 * Default constructor.
	 */
	public ReplacerFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		final String[] stopLists = request.getParameterValues("stopList");
		if (stopLists != null) {
			boolean isCleared = false;
			for (final String listElem : stopLists) {
				if (!listElem.isEmpty()) {
					if (!isCleared) {
						itsStopList.clear();
						isCleared = true;
					}
					for (final String word : listElem.split(",")) {
						itsStopList.add(word.trim());
					}
				}
			}
		}
		final String stopListString = itsStopList.toString();
		request.setAttribute("StopList", stopListString.substring(1, stopListString.length() - 1));

		final String[] inputTexts = request.getParameterValues("text");
		if (inputTexts != null) {
			final StringBuffer filteredText = new StringBuffer();
			final StringBuffer originalText = new StringBuffer();
			for (String textElem : inputTexts) {
				originalText.append(textElem);
				for (final String stopWord : itsStopList) {
					textElem = textElem.replaceAll(stopWord, "").trim();
				}
				filteredText.append(textElem);
			}
			request.setAttribute("FilteredText", filteredText.toString());
			request.setAttribute("OriginalText", originalText.toString());
		} else {
			request.setAttribute("FilteredText", "");
			request.setAttribute("OriginalText", "");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
		final String stopList = fConfig.getInitParameter("StopList");
		for (final String word : stopList.split(",")) {
			itsStopList.add(word);
		}
	}

}
