package DAO;

import java.util.ArrayList;
import DTO.BoardBean;

public interface Board {
	public ArrayList<BoardBean> bList();
	public BoardBean bSelect(int index);
	public int bDelete(int index);
	public int bUpdate(BoardBean bean);
	public int bInsert(BoardBean bean);
	public ArrayList<BoardBean> bSearch(String option, String keyword);
}
