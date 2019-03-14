package sa.zad.pagedrecyclerlist;

public class PageDataModel<P, M> extends DataModel<M> {

  public P page;
  public P next_page;
  public P previous_page;
  public int count;
  public int maxPages;
  public int total;

}
