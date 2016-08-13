package DAO;

import java.util.ArrayList;
import DTO.BoardBean;

public interface Board {
	public ArrayList<BoardBean> bList();
	public BoardBean bSelect(int bseq);
	public int bDelete(int bseq);
	public int bUpdate(BoardBean bean);
	public int bInsert(BoardBean bean);
}
