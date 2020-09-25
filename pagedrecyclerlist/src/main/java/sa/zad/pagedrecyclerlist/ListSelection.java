package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sa-ZAD on 2018-01-07.
 */

public abstract class ListSelection<M, I extends View & AppListAdapter.AppAdapterItem<M>>
        extends AppList<M, I> {

    public static final int NOT_FOUND = -1;
    public static final int MAX_SELECTION = -1;
    public static final int NO_SELECTION = 0;
    public static final int SINGLE_SELECTION = 1;
    public boolean radioSelection = false;
    private int selectionCount = NO_SELECTION;
    private SelectedCountListener selectedCountListener = count -> {

    };

    private ItemOnBindListener<M, I> itemOnBindListener = (item, itemView, index) -> {
    };

    private ItemOnClickListener<M, I> itemOnClickListener = (item, itemView, index) -> {
    };


    private List<M> mSelected = new ArrayList<>();
    private ListSelectionListener<M> mListSelectorListener =
            new ListSelectionListener<M>() {
                @Override
                public void selected(@NonNull M item, @NonNull List<M> selectedList) {

                }

                @Override
                public void unSelected(@NonNull M item, @NonNull List<M> selectedList) {

                }
            };

    public ListSelection(Context context) {
        this(context, null);
    }

    public ListSelection(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListSelection(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getCount() {
        return selectionCount;
    }


    public void setRadioSelection(boolean radioSelection) {
        this.radioSelection = radioSelection;
    }

    /**
     * call this method will reset selections
     */
    final public void setSelectionCount(int selectionCount) {
        this.selectionCount = selectionCount;
        removeAllSelected();
    }

    /**
     * call this method will reset selections
     */
    final public void setSelectionCount(int selectionCount, boolean radioSelection) {
        setSelectionCount(selectionCount);
        this.radioSelection = radioSelection;
    }

    /**
     * call this method will reset selections
     */
    final public void setMaxSelection() {
        setSelectionCount(MAX_SELECTION);
        this.radioSelection = false;
    }

    /**
     * call this method will reset selections
     */
    final public void setSingleSelection() {
        setSelectionCount(SINGLE_SELECTION);
        this.radioSelection = true;
    }

    final public void setSelectionListener(ListSelectionListener<M> listSelectorListener) {
        mListSelectorListener = listSelectorListener;
    }

    final public void setSelectedCountListener(SelectedCountListener selectedCountListener) {
        this.selectedCountListener = selectedCountListener;
    }

    public final void addSelectedItem(M item) {
        selection(item);
    }

    public final void addSelectedItems(List<M> items) {
        for (M item : items) {
            addSelectedItem(item);
        }
    }

    final public void removeSelectedItems(List<M> items) {
        for (M item : items) {
            removeSelectedItem(item);
        }
    }

    final public void removeSelectedItem(M item) {
        final int foundItemIndex = compareSelectedItems(item);
        if (foundItemIndex != NOT_FOUND) {
            mSelected.remove(foundItemIndex);
            callSelectedCountListener();
        }
    }

    final public void removeAllSelected() {
        mSelected.clear();
        callSelectedCountListener();
        getListAdapter().notifyDataSetChanged();
    }

    @Override
    public final I getItemView(@NonNull Context context, int viewType) {
        return getSelectorItem(context, viewType);
    }

    @CallSuper
    @Override
    public boolean onBindItemView(@NonNull I view, @NonNull M item, int itemIndex) {
        final View selectionView = view.selectionView();

        if (selectionView == view) {
            selectionView.setOnClickListener(__ -> {
                selectionToggle(view);
                itemOnClickListener.itemCall(view.getItem(), view, itemIndex);
            });
        } else {
            selectionView.setOnClickListener(__ -> {
                selectionToggle(view);
            });
            view.setOnClickListener(__ -> {
                itemOnClickListener.itemCall(view.getItem(), view, itemIndex);
            });
        }

        itemOnBindListener.itemCall(item, view, itemIndex);
        view.bind(item);
        if (isSelectionList()) {
            final int foundItemIndex = compareSelectedItems(view.getItem());
            view.select(foundItemIndex != NOT_FOUND);
        }

        view.lastItem(getListAdapter().getItemCount() - 1 == itemIndex);
        return true;
    }

    final public List<M> getSelected() {
        return mSelected;
    }

    private int compareSelectedItems(M compareWith) {
        for (int i = 0; i < mSelected.size(); i++) {
            if (compare(compareWith, mSelected.get(i))) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    final public void selectionToggle(I itemView) {
        if (selectionCount == NO_SELECTION) {
            return;
        }

        int count = selectionCount == MAX_SELECTION ? getListAdapter().getItemCount() : selectionCount;

        final M item = itemView.getItem();
        final int foundItemIndex = compareSelectedItems(item);
        if (foundItemIndex != NOT_FOUND) {
            if (!radioSelection) {
                removeSelection(foundItemIndex);
                unSelected(itemView, item, mSelected);
                selectedItemModified(item);
            }
        } else {
            if (shouldRemoveLimitSelection()) {
                //remove first selected item
                removeSelection(0);
            }
            addSelection(item);
            selected(itemView, item, mSelected);
            selectedItemModified(item);
        }
    }

    private void selectedItemModified(M item) {
        getListAdapter().notifyItemChanged(getListAdapter().getItems().indexOf(item));
        callSelectedCountListener();
    }

    private void selection(M item) {
        if (selectionCount == NO_SELECTION) {
            return;
        }

        final int foundItemIndex = compareSelectedItems(item);

        if (foundItemIndex == NOT_FOUND) {
            if (shouldRemoveLimitSelection()) {
                //remove first selected item
                removeSelection(0);
            }
            mSelected.add(item);
            callSelectedCountListener();
        }
    }

    private boolean shouldRemoveLimitSelection() {
        return selectionCount != MAX_SELECTION && mSelected.size() >= selectionCount && !mSelected.isEmpty();
    }

    private void callSelectedCountListener() {
        selectedCountListener.count(mSelected.size());
    }

    final public boolean isSelectionList() {
        return selectionCount != NO_SELECTION;
    }

    final public boolean isSingleSelection() {
        return selectionCount == 1;
    }

    /**
     * call this method will reset selections
     */
    final public void setSingleSelection(boolean radioSelection) {
        setSingleSelection();
        this.radioSelection = radioSelection;
    }

    final public boolean isSelected(M item) {
        final int foundItemIndex = compareSelectedItems(item);
        return foundItemIndex != NOT_FOUND;
    }

    private void addSelection(M item) {
        mSelected.add(item);
        mListSelectorListener.selected(item, mSelected);
    }

    private void removeSelection(int foundItemIndex) {
        final M remove = mSelected.remove(foundItemIndex);
        mListSelectorListener.unSelected(remove, mSelected);
        getListAdapter().notifyItemChanged(getListAdapter().getItems().indexOf(remove));
    }

    public void setItemOnBindListener(ItemOnBindListener<M, I> itemOnBindListener) {
        this.itemOnBindListener = itemOnBindListener;
    }

    public void setItemOnClickListener(ItemOnClickListener<M, I> itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public abstract boolean compare(M item1, M item2);

    public void selected(I view, M item, List<M> selectedList) {
    }

    public void unSelected(I view, M item, List<M> selectedList) {
    }

    public abstract I getSelectorItem(Context context, int viewType);

    public interface ListSelectionListener<T> {
        void selected(@NonNull T item, @NonNull List<T> selectedList);

        void unSelected(@NonNull T item, @NonNull List<T> selectedList);
    }

    public interface SelectedCountListener {
        void count(int count);
    }
}
