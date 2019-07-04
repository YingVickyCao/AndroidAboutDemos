package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import androidx.annotation.NonNull;

public class VHPositionHashCodeBean {
    public int position;
    public int hashCode;


    public VHPositionHashCodeBean() {
    }

    public VHPositionHashCodeBean(int position, int hashCode) {
        this.position = position;
        this.hashCode = hashCode;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (null != obj) {
            if (obj instanceof VHPositionHashCodeBean) {
                VHPositionHashCodeBean tmp = (VHPositionHashCodeBean) obj;
                return tmp.hashCode == this.hashCode;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return "pos " + position + " @ " + hashCode + "\n";
    }
}
