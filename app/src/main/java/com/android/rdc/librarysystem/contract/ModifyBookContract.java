package com.android.rdc.librarysystem.contract;

import com.android.rdc.librarysystem.bean.Book;

public interface ModifyBookContract {
    interface Model {
        void getBookData(long bookId, OnBookGetListener listener);

        void deleteBook(long id, OnBookDeleteListener listener);

        void modifyBook(Book book, OnBookModifyListener listener);

        interface OnBookGetListener {
            void onBookGet(Book book);
        }

        interface OnBookDeleteListener {
            void onBookDelete(int rowAffect);
        }

        interface OnBookModifyListener {
            void onBookModify(int rowAffect);
        }
    }

    interface View {
        void displayBookData(Book book);

        void showBookDeleteResult(int rowAffect);

        void showBookModifyResult(int rowAffect);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void onResume(long bookId);

        void onDestroy();

        void deleteBook(long id);

        void modifyBook(Book book);
    }
}
