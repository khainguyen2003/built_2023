package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Error
 */
@WebServlet("/error")
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();

		String err = request.getParameter("err");
		if (err != null) {
			out.append("<div class=\"toast-container position-fixed top-0 start-50 translate-middle-x p-3\">");
			out.append(
					"<div id=\"liveToast\" class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">");
			out.append("<div class=\"toast-header\">");
			out.append("<i class=\"bi bi-x-circle-fill\"></i>");
			out.append("<strong class=\"me-auto\">Có lỗi</strong>");
			out.append("<small>tại module Người sử dụng</small>");
			out.append(
					"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"toast-body\">");

			switch (err) {
			case "add": 
				out.append("Lỗi khi cập nhật thêm mới");
				break;
			case "edit":
				out.append("Có lỗi khi sửa thông tin");
				break;
			case "del":
				out.append("Có lỗi khi thực hiện xóa");
				break;
			case "valueadd": 
				out.append("Lỗi khi lấy giá trị thêm mới");
				break;
			case "upd":
				out.append("Có lỗi khi lấy giá trị sửa thông tin");
				break;
			case "nopermis":
				out.append("Không có đủ quyền để xóa");
				break;
			case "acclogin":
				out.append("Có lỗi khi lấy giá trị xóa");
				break;
			default:
				out.append("Có lỗi xin vui lòng kiểm tra lại");
			}
		}
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<script>");
		out.append("const viewToast = document.getElementById('liveToast');");
		out.append("const toast = new bootstrap.Toast(viewToast);");
		out.append("toast.show();");
		out.append("</script>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
