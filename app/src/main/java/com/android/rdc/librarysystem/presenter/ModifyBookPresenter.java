package com.android.rdc.librarysystem.presenter;

import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.contract.ModifyBookContract;
import com.android.rdc.librarysystem.model.ModifyBookModel;

public class ModifyBookPresenter implements ModifyBookContract.Presenter {
    private ModifyBookContract.Model mModel;
    private ModifyBookContract.View mView;

    public ModifyBookPresenter(ModifyBookContract.View view) {
        mView = view;
        mModel = new ModifyBookModel();
    }

    @Override
    public void onResume(long bookId) {
        mView.showProgress();
        mModel.getBookData(bookId, book -> {
            mView.hideProgress();
            mView.displayBookData(book);
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void deleteBook(long id) {
        mModel.deleteBook(id, rowAffect -> mView.showBookDeleteResult(rowAffect));
    }

    @Override
    public void modifyBook(Book book) {
        mModel.modifyBook(book, rowAffect -> {
            mView.hideProgress();
            mView.showBookModifyResult(rowAffect);
        });
    }
}
