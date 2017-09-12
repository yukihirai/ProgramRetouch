package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.ItemDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String buy_id = request.getParameter("buy_id");
		int buyId = Integer.parseInt(buy_id);
		try {
			BuyDataBeans bdb = (BuyDataBeans)BuyDAO.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("bdb", bdb);
			List<ItemDataBeans> idb = (List<ItemDataBeans>)ItemDAO.getItemByBuyId(buyId);
			request.setAttribute("idb", idb);

		} catch (SQLException e) {
			e.printStackTrace();
		}


		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}
}
