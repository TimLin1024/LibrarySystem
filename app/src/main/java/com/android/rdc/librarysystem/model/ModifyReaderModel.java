package com.android.rdc.librarysystem.model;

import android.content.ContentValues;

import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.contract.ModifyReaderContract;

import org.litepal.crud.DataSupport;

public class ModifyReaderModel implements ModifyReaderContract.Model {

    @Override
    public void getReaderData(long id, OnReaderGetListener listener) {
        Reader reader = DataSupport.find(Reader.class, id);
        listener.onReaderGet(reader);
    }

    @Override
    public void deleteReader(long id, OnReaderDeleteListener listener) {
        int rowAffect = DataSupport.delete(Reader.class, id);//级联删除
        listener.onReaderDelete(rowAffect);
    }

    @Override
    public void modifyReader(Reader reader, OnReaderModifyListener listener) {
        //update 无法更新外键，试用 save。 save 会导致关联表的外键丢失 不可行，应该使用原生的 update
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", reader.getName());
        contentValues.put("readertype_id", reader.getReaderType().getId());
        contentValues.put("company", reader.getCompany());
        contentValues.put("gender", reader.getGender());
        contentValues.put("address", reader.getAddress());
        contentValues.put("phonenum", reader.getPhoneNum());
        contentValues.put("email", reader.getEmail());
        contentValues.put("enrolldate", reader.getEnrollDate().getTime());
        contentValues.put("remark", reader.getRemark());
        int rowAffect = DataSupport.update(Reader.class, contentValues, reader.getId());

        listener.onReaderModify(rowAffect);
    }

}
