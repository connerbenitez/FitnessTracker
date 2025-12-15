'use strict';
const dbConn = require('../../config/db.config');

class DietGoal {
    constructor(dietgoal) {
        this.diet_goal_id = dietgoal.diet_goal_id; // Primary key
        //this.diet_id = dietgoal.diet_id; // Foreign key referencing a diet
        this.user_id = dietgoal.user_id; // Foreign key referencing a user
        this.description = dietgoal.description;
        this.completion = dietgoal.completion; // Boolean
        this.start_date = dietgoal.start_date;
        this.end_date = dietgoal.end_date;
        this.calorie_goal = dietgoal.calorie_goal;
    }

    // CREATE
    static create(dietgoal, result) {
        const insertData = {
            //diet_id: dietgoal.diet_id,
            user_id: dietgoal.user_id,
            description: dietgoal.description,
            completion: dietgoal.completion,
            start_date: dietgoal.start_date,
            end_date: dietgoal.end_date,
            calorie_goal: dietgoal.calorie_goal
        };

        dbConn.query("INSERT INTO dietgoal SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    // FIND BY ID
    static findById(diet_goal_id, result) {
        dbConn.query(
            "SELECT * FROM dietgoal WHERE diet_goal_id = ?",
            [diet_goal_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND ALL
    static findAll(result) {
        dbConn.query("SELECT * FROM dietgoal", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // FIND BY USER
    static findByUserId(user_id, result) {
        dbConn.query(
            "SELECT * FROM dietgoal WHERE user_id = ?",
            [user_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY DIET ID
    static findByDietId(diet_id, result) {
        dbConn.query(
            "SELECT * FROM dietgoal WHERE diet_id = ?",
            [diet_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY START DATE
    static findByStartDate(start_date, result) {
        dbConn.query(
            "SELECT * FROM dietgoal WHERE start_date = ?",
            [start_date],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY END DATE
    static findByEndDate(end_date, result) {
        dbConn.query(
            "SELECT * FROM dietgoal WHERE end_date = ?",
            [end_date],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // UPDATE
        static update(goal_id, goalData, result) {
            const sql = `
                UPDATE dietgoal 
                SET user_id = ?, description = ?, start_date = ?, end_date = ?, 
                    completion = ?, calorie_goal = ?
                WHERE diet_goal_id = ?
            `;
    
            const params = [
                goalData.user_id,
                goalData.description,
                goalData.start_date,
                goalData.end_date,
                goalData.completion,
                goalData.calorie_goal,
                goal_id
            ];
    
            dbConn.query(sql, params, (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            });
        }

    // UPDATE COMPLETION STATUS
    static updateCompletion(goal_id, completion, result) {
        const sql = `
            UPDATE dietgoal
            SET completion = ?
            WHERE diet_goal_id = ?
        `;

        const params = [completion, goal_id]; // use the parameter 'completion'

        dbConn.query(sql, params, (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // DELETE
    static delete(diet_goal_id, result) {
        dbConn.query(
            "DELETE FROM dietgoal WHERE diet_goal_id = ?",
            [diet_goal_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }
}

module.exports = DietGoal;
