package com.smartreal.api.mapper;

import com.smartreal.api.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT id, username, password, phone, email, full_name, date_time FROM smart_real.user WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Update("UPDATE " +
            "   smart_real.user " +
            "SET " +
            "   password = #{password}," +
            "WHERE " +
            "   username = #{username}")
    void changePasswordByUser(@Param("username") String username,@Param("password") String newPassword);

    @Insert("INSERT INTO " +
            "   smart_real.user(username, password, full_name, email, phone) " +
            "VALUES " +
            "   (#{username}, #{password}, #{fullName}, #{email}, #{phone})")
    void insertUser(User user);

    @Select("SELECT id, username, password, full_name FROM smart_real.user WHERE phone = #{phone}")
    User getUserByPhone(@Param("phone") String phone);

    @Select("SELECT id, username, password, full_name FROM smart_real.user WHERE email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Update("UPDATE smart_real.user " +
            "SET " +
            "   username = #{username}, " +
            "   phone = #{phone}, " +
            "   email = #{email}, " +
            "   full_name = #{fullName} " +
            "WHERE " +
            "   id = #{id}")
    void updateUser(User user);

    @Select("SELECT" +
            "   id, username, phone, email, full_name, date_time " +
            "FROM" +
            "   smart_real.user " +
            "WHERE" +
            "   id = #{id}")
    User getUserById(Long id);
}
