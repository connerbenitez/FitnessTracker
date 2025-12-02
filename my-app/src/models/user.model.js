'use strict';
var dbConn = require('../../config/db.config');
const bcrypt = require('bcrypt');
const saltRounds = 10;

//User object create
var User = function (user) {
    this.firstname = user.firstname;
    this.lastname = user.lastname;
    this.email = user.email;
    this.role = user.role;
    this.password = user.password;
    this.status = user.status ? user.status : 1;
    this.created_at = new Date();
    this.updated_at = new Date();
};
User.create = function (user, result) {

    //encrypt password
    bcrypt.hash(user.password, saltRounds, function(err, hash) {
        // Store hash in your password DB.
        user.password=hash;
        dbConn.query("INSERT INTO users set ?", user, function (err, res) {
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
    var sql="Select * from users where email = ? ";
    sql="SELECT * FROM `users` LEFT JOIN employees on users.email=employees.email where users.email = ?"
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
    dbConn.query("SELECT * FROM users LEFT JOIN employees on users.email=employees.email where users.email= ? ", id, function (err, res) {
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
    dbConn.query("Select * from users where id = ? ", id, function (err, res) {
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
    dbConn.query("Select * from users", function (err, res) {
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
    dbConn.query("UPDATE users SET firstname=?,lastname=? WHERE email = ?", [user.firstname, user.lastname, user.email], function (err, res) {
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
        
        dbConn.query("UPDATE users SET password=? WHERE email = ?", [hash,  email], function (err, res) {
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
    dbConn.query("DELETE FROM users WHERE email = ?", [email], function (err, res) {
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