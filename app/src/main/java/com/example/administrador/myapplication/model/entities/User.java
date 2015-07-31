package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.SQLiteUserRepository;

/**
 * Created by Administrador on 30/07/2015.
 */
public class User implements Parcelable {
    private Integer id;
    private String name;
    private String password;

    public User() {
        super();
    }
    public User(Parcel in) {
        super();
        readToParcel(in);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authentication(){return SQLiteUserRepository.getInstance().authentication(this);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
    }

    private void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        name = in.readString();
        password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
