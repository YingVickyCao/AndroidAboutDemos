package com.hades.android.example.room;

import android.database.Cursor;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TeachDao {
    /**
     * Insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourses(Course... courses);

    @Insert
    long insertStudent(Student student);

    @Insert
    long[] insertStudents(List<Student> students);

    @Insert
    List<Long> insertScores(List<Score> scores);

    @Insert
    void insertScores(Score score1, Score score2);

    @Insert
    void insertScores(Score score1, List<Score> scores);

    /**
     * DELETE
     */
    @Query("DELETE FROM stu")
    void deleteStudents();

    @Query("DELETE FROM score")
    void deleteScores();

    @Query("DELETE FROM score WHERE score=:score")
    void deleteScores(int score);

    @Query("DELETE FROM score WHERE score=:score AND course_id=:course_id")
    void deleteScores(int score, int course_id);

    @Delete
    void deleteScoresUsePrimaryKey(Score score);// It uses the primary keys to find the entities to delete.

    @Delete
    void deleteScores(Score... scores);

    @Delete
    int deleteScores2(Score... scores);

    @Delete
    int deleteScores(List<Score> scores);

    /**
     * Update
     */
    @Update
    int updateScore(Score score);//Depressed:side effect：modify temp column

    @Update
    void updateScore(Score... scores);//Depressed:side effect：modify temp column

    @Update
    int updateScore2(Score... scores);//Depressed:side effect：modify temp column

    @Update
    int updateScore(List<Score> scores);//Depressed:side effect：modify temp column

    @Query("UPDATE score SET score=:newScore WHERE course_id =:courseId AND stu_id=:stuId")
    int updateScore(int newScore, int courseId, int stuId);

    /**
     * Query
     */
    @Query("SELECT * FROM course")
    List<Course> getCourseAll();

    @Query("SELECT * FROM stu")
    List<Student> getStudentAll();

    @Query("SELECT * FROM stu WHERE stu_name LIKE :search OR stu_name LIKE :search2")
    List<Student> findStudentUseName(String search, String search2);

    @Query("SELECT * FROM score")
    List<Score> getScoreAll();

    @Query("SELECT * FROM score WHERE score>:minScore")
    List<Score> getScores(int minScore);

    @Query("SELECT * FROM score WHERE score BETWEEN :minScore AND :maxScore ORDER BY score ASC")
    Score[] getScores(int minScore, int maxScore);

    // Returning subsets of columns
    @Query("SELECT course_id, score FROM score WHERE course_id IN (:courseIds)")
    List<ScoreTuple> getScores(int[] courseIds);

    @Query("SELECT course_id, score FROM score")
    List<ScoreTuple> getScores();

    @Query("SELECT * FROM score WHERE stu_id LIKE :first OR course_id LIKE :last LIMIT 1")
    Score getScore(int first, int last);

    @Query("SELECT * FROM score WHERE stu_id LIKE :first OR course_id LIKE :last LIMIT 5")
    List<Score> getScore2(int first, int last);

    @Query("SELECT * FROM score WHERE score > :minScore LIMIT 2")
    Cursor loadScoresGreaterThan(int minScore);

    @Query("SELECT stu.stu_name, course.course_name, score FROM score " +
            "INNER JOIN stu ON stu.stu_id = score.stu_id " +
            "INNER JOIN course ON course.course_id = score.course_id " +
            "WHERE score.score > :minScore")
    List<ScoreStuCourseTuple> findScoreGreatThan(int minScore); // 多表联合查询,http://www.runoob.com/sql/sql-join-inner.html

    @Query("SELECT stu.stu_name, course.course_name, score FROM score,stu,course " +
            "WHERE stu.stu_id = score.stu_id AND course.course_id = score.course_id AND score.score > :minScore")
    List<ScoreStuCourseTuple> findScoreGreatThan2(int minScore); // 多表联合查询

    // TODO: 2019/4/4 Use LiveData with Room
//    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
//    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);
//

}