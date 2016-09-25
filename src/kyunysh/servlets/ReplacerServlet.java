package kyunysh.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplacerServlet
 */
@WebServlet("/ReplacerServlet")
public class ReplacerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplacerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final PrintWriter writer = response.getWriter();
		writer.append("<!DOCTYPE html>");
		writer.append("<html>");
		writer.append("<head>");
		writer.append("<title>Replacer Servlet</title>");
		writer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=")
				.append(response.getCharacterEncoding()).append("\">");
		writer.append("</head>");
		writer.append("<body>");
		writer.append("<form>");
		writer.append("<p>");
		writer.append("<label>Stop list: </label>");
		writer.append("<input type=\"text\" name=\"stopList\" value=\"\">");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<label>Current list: ").append(request.getAttribute("StopList").toString()).append("</label>");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<label>Example: а,б,день </label>");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<input type=\"submit\" value=\"Задать стоп лист\">");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<label>Text: </label>");
		writer.append("<input type=\"text\" name=\"text\" value=\"")
				.append(request.getAttribute("OriginalText").toString()).append("\">");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<input type=\"submit\" value=\"Отправить текст\">");
		writer.append("</p>");
		writer.append("<p>");
		writer.append("<label>");
		writer.append("Filtered text: ").append(request.getAttribute("FilteredText").toString());
		writer.append("</label>");
		writer.append("</p>");
		writer.append("</form>");
		writer.append("</body>");
		writer.append("</html>");
	}

}
