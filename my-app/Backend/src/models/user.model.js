'use strict';
var dbConn = require('../../config/db.config');
const bcrypt = require('bcrypt');
const saltRounds = 10;

//User object create
var User = function (user) {
    this.userid = user.userid;
    this.username = user.username;
    this.password = user.password;
    this.first_name= user.first_name;
    this.last_name = user.last_name;
    this.email = user.email;
    this.start_date = user.start_date;
    this.status = user.status ? user.status : 1;
};
User.create = function (user, result) {

    //encrypt password
    bcrypt.hash(user.password, saltRounds, function(err, hash) {
        // Store hash in your password DB.
        user.password=hash;
        dbConn.query("INSERT INTO user set ?", user, function (err, res) {
            if (err) {
                console.log("errors: ", err);
                result(err, null);
            }
            else {
                console.log(res.insertId);
                result(null, res.insertId);
                // result(null, user.email);
            }
        });
    });

    
};

User.login = function (email,password, result) {
    var sql="Select * from user where email = ? ";
    sql="SELECT * FROM `user`  where user.email = ?"
    dbConn.query(sql, email, function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(err, null);
        }
        else {
            
            if(res.length==0){result(err, null); } //invalid email
            else{
             bcrypt.compare(password, res[0].password, function(err, result_) {
                 
                 if (result_==true) {
                     // password is valid
                     result(null, res[0]);
                 }else{
                     result(err, null);
                 }
                 });
             }   
         }
     });
};


User.findByEmail = function (id, result) {
    dbConn.query("SELECT * FROM user where user.email= ? ", id, function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(err, null);
        }
        else {
            result(null, res);
        }
    });
};


User.findById = function (id, result) {
    dbConn.query("Select * from user where user_id = ? ", id, function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(err, null);
        }
        else {
            result(null, res);
        }
    });
};

User.findAll = function (result) {
    dbConn.query("Select * from user", function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(null, err);
        }
        else {
            console.log('users : ', res);
            result(null, res);
        }
    });
};


User.update = function (user, result) {
    console.log(user)
    dbConn.query("UPDATE user SET first_name=?,last_name=? WHERE email = ?", [user.first_name, user.last_name, user.email], function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(null, err);
        } else {
            result(null, res);
        }
    });
};

User.updatePwd = function (password, email, result) {
    
    bcrypt.hash(password, saltRounds, function(err, hash) {
        // Store hash in your password DB.
        
        dbConn.query("UPDATE user SET password=? WHERE email = ?", [hash,  email], function (err, res) {
            if (err) {
                console.log("errors: ", err);
                result(null, err);
            } else {
                result(null, res);
            }
        });
      
    });
    
   
};

User.delete = function (email, result) {
    dbConn.query("DELETE FROM user WHERE email = ?", [email], function (err, res) {
        if (err) {
            console.log("errors: ", err);
            result(null, err);
        }
        else {
            result(null, res);
        }
    });
};
module.exports = User;