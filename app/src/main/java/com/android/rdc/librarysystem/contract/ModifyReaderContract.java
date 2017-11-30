package com.android.rdc.librarysystem.contract;

import com.android.rdc.librarysystem.bean.Reader;

public interface ModifyReaderContract {
    interface Model {
        void getReaderData(long readerId, OnReaderGetListener listener);

        void deleteReader(long id, OnReaderDeleteListener listener);

        void modifyReader(Reader reader, OnReaderModifyListener listener);

        interface OnReaderGetListener {
            void onReaderGet(Reader reader);
        }

        interface OnReaderDeleteListener {
            void onReaderDelete(int rowAffect);
        }

        interface OnReaderModifyListener {
            void onReaderModify(int rowAffect);
        }
    }

    interface View {
        void displayReaderData(Reader reader);

        void showReaderDeleteResult(int rowAffect);

        void showReaderModifyResult(int rowAffect);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void onResume(long readerId);

        void onDestroy();

        void deleteReader(long id);

        void modifyReader(Reader reader);
    }
}
