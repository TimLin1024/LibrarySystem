package com.android.rdc.librarysystem.presenter;

import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.contract.ModifyReaderContract;
import com.android.rdc.librarysystem.model.ModifyReaderModel;

public class ModifyReaderPresenter implements ModifyReaderContract.Presenter {
    private ModifyReaderContract.Model mModel;
    private ModifyReaderContract.View mView;

    public ModifyReaderPresenter(ModifyReaderContract.View view) {
        mView = view;
        mModel = new ModifyReaderModel();
    }

    @Override
    public void onResume(long readerId) {
        mView.showProgress();
        mModel.getReaderData(readerId, Reader -> {
            mView.hideProgress();
            mView.displayReaderData(Reader);
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void deleteReader(long id) {
        mModel.deleteReader(id, rowAffect -> mView.showReaderDeleteResult(rowAffect));
    }

    @Override
    public void modifyReader(Reader reader) {
        mModel.modifyReader(reader, rowAffect -> {
            mView.hideProgress();
            mView.showReaderModifyResult(rowAffect);
        });
    }

}
