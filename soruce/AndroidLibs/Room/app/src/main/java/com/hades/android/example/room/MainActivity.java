package com.hades.android.example.room;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    TextView mQueryResultView;
    TeachDao teachDao;
    private View mProgressBarLayout;
    private View mBtnLayout;
    private Mock mMock = new Mock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueryResultView = findViewById(R.id.queryResult);
        mProgressBarLayout = findViewById(R.id.progressBarLayout);
        mBtnLayout = findViewById(R.id.btnLayout);

        findViewById(R.id.insertCourseAll).setOnClickListener(v -> insertCourseAll());
        findViewById(R.id.insertStudent).setOnClickListener(v -> insertStudent());
        findViewById(R.id.insertStudents).setOnClickListener(v -> insertStudents());
        findViewById(R.id.insertScores_List).setOnClickListener(v -> insertScores_List());
        findViewById(R.id.insertScores_both).setOnClickListener(v -> insertScores_both());
        findViewById(R.id.insertScores_one_and_list).setOnClickListener(v -> insertScores_one_and_list());

        findViewById(R.id.deleteStudentAll).setOnClickListener(v -> deleteStudentAll());
        findViewById(R.id.deleteScoreAll).setOnClickListener(v -> deleteScoreAll());
        findViewById(R.id.deleteScores).setOnClickListener(v -> deleteScores());
        findViewById(R.id.deleteScores_And).setOnClickListener(v -> deleteScores_And());
        findViewById(R.id.deleteScores_PrimaryKey).setOnClickListener(v -> deleteScores_PrimaryKey());
        findViewById(R.id.deleteScores_Dot).setOnClickListener(v -> deleteScores_Dot());
        findViewById(R.id.deleteScores_Dot2).setOnClickListener(v -> deleteScores_Dot2());
        findViewById(R.id.deleteScores_List).setOnClickListener(v -> deleteScores_List());

        findViewById(R.id.updateScore).setOnClickListener(v -> updateScore());
        findViewById(R.id.updateScore_Dot).setOnClickListener(v -> updateScore_Dot());
        findViewById(R.id.updateScore_Dot2).setOnClickListener(v -> updateScore_Dot2());
        findViewById(R.id.updateScore_List).setOnClickListener(v -> updateScore_List());
        findViewById(R.id.updateScore_Query).setOnClickListener(v -> updateScore_Query());

        findViewById(R.id.queryCourseAll).setOnClickListener(v -> queryCourseAll());
        findViewById(R.id.queryStudentAll).setOnClickListener(v -> queryStudentAll());
        findViewById(R.id.findStudentUseName).setOnClickListener(v -> findStudentUseName());
        findViewById(R.id.queryScoreAll).setOnClickListener(v -> queryScoreAll());
        findViewById(R.id.queryScore_bigThan).setOnClickListener(v -> queryScore_bigThan());
        findViewById(R.id.queryScore_BetweenAnd).setOnClickListener(v -> queryScore_BetweenAnd());
        findViewById(R.id.queryScore_In).setOnClickListener(v -> queryScore_In());
        findViewById(R.id.queryScore_Subsets).setOnClickListener(v -> queryScore_Subsets());
        findViewById(R.id.queryScore_Limit).setOnClickListener(v -> queryScore_Limit());
        findViewById(R.id.queryScore_LimitV2).setOnClickListener(v -> queryScore_LimitV2());
        findViewById(R.id.queryScore_Cursor).setOnClickListener(v -> queryScore_Cursor());
        findViewById(R.id.queryScores_Inner_Join).setOnClickListener(v -> queryScores_Inner_Join());
        findViewById(R.id.queryScores_MultiTableAnd).setOnClickListener(v -> queryScores_MultiTableAnd());

        teachDao = ((App) getApplication()).getAppDatabase().teachDao();
    }

    private void insertCourseAll() {
        showProgressBar();
        new Thread(() -> {
            teachDao.insertCourses(mMock.buildCourse(1, "PMP"), mMock.buildCourse(2, "Agile"));

            hideProgressBar();
        }).start();
    }

    private void insertStudent() {
        showProgressBar();

        new Thread(() -> {
            try {
                long rowId = teachDao.insertStudent(mMock.buildStudent(1, "Steve Jobs"));
                Log.d(TAG, "insertStudent: rowId=" + rowId);
            } catch (SQLiteConstraintException ex) {// FIXED_ERROR：android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: stu.stu_id (code 1555)
                showToast("UNIQUE constraint failed: stu.stu_id");
            }

            hideProgressBar();
        }).start();
    }

    private void insertStudents() {
        showProgressBar();

        new Thread(() -> {
            try {
                long[] rowIds = teachDao.insertStudents(mMock.buildStudents());
                Log.d(TAG, "insertStudents: " + mMock.printLongs(rowIds));
            } catch (SQLiteConstraintException ex) {
                showToast("UNIQUE constraint failed: stu.stu_id");
            }

            hideProgressBar();
        }).start();
    }

    private void insertScores_List() {
        showProgressBar();

        new Thread(() -> {
            try {
                List<Long> rowIds = teachDao.insertScores(mMock.buildScores_v1());
                Log.d(TAG, "insertScores_List: " + mMock.printLongs(rowIds));
            } catch (SQLiteConstraintException ex) {
                Log.d(TAG, "insertScores_List: " + ex.getMessage());
            }
            hideProgressBar();

        }).start();
    }

    private void insertScores_both() {
        showProgressBar();

        new Thread(() -> {
            try {
                teachDao.insertScores(mMock.buildScore(1, 3, 75, 3), mMock.buildScore(2, 1, 99, 4));
            } catch (SQLiteConstraintException ex) {
                Log.d(TAG, "insertScores_both: " + ex.getMessage());
            }
            hideProgressBar();
        }).start();
    }

    private void insertScores_one_and_list() {
        showProgressBar();
        new Thread(() -> {
            try {
                teachDao.insertScores(mMock.buildScore(2, 2, 60, 5), mMock.buildScores_v3());
            } catch (SQLiteConstraintException ex) {
                Log.d(TAG, "insertScores_both: " + ex.getMessage());
            }
            hideProgressBar();
        }).start();
    }


    private void deleteStudentAll() {
        showProgressBar();
        new Thread(() -> {
            teachDao.deleteStudents();

            hideProgressBar();
        }).start();
    }

    private void deleteScoreAll() {
        showProgressBar();
        new Thread(() -> {
            teachDao.deleteScores();
            hideProgressBar();
        }).start();
    }

    private void deleteScores() {
        showProgressBar();
        new Thread(() -> {
            teachDao.deleteScores(100);

            hideProgressBar();
        }).start();
    }

    private void deleteScores_And() {
        showProgressBar();
        new Thread(() -> {
            teachDao.deleteScores(60, 2);

            hideProgressBar();
        }).start();
    }

    private void deleteScores_PrimaryKey() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.stuId = 1;
            score.courseId = 1;

            teachDao.deleteScoresUsePrimaryKey(score);

            hideProgressBar();
        }).start();
    }

    private void deleteScores_Dot() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;

            teachDao.deleteScores(score, score2);

            hideProgressBar();
        }).start();
    }

    private void deleteScores_Dot2() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;

            int n = teachDao.deleteScores2(score, score2);
            Log.d(TAG, "deleteScores_Dot2: n=" + n);

            hideProgressBar();
        }).start();
    }

    private void deleteScores_List() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;

            List<Score> list = new ArrayList<>();
            list.add(score);
            list.add(score2);

            int n = teachDao.deleteScores(list);
            Log.d(TAG, "deleteScores_List: n=" + n);

            hideProgressBar();
        }).start();
    }

    private void updateScore() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;
            score.score = 50;

            // TODO: 2019/4/4 side effect：modify temp column
            int n = teachDao.updateScore(score);

            Log.d(TAG, "updateScore: n=" + n);

            hideProgressBar();
        }).start();
    }

    private void updateScore_Dot() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;
            score.score = 50;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;
            score2.score = 51;

            // TODO: 2019/4/4 side effect：modify temp column
            teachDao.updateScore(score, score2);

            hideProgressBar();
        }).start();
    }

    private void updateScore_Dot2() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;
            score.score = 50;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;
            score2.score = 51;

//            List<Score> list = new ArrayList<>();
//            list.add(score);
//            list.add(score2);

            // TODO: 2019/4/4 side effect：modify temp column
            int updatedNum = teachDao.updateScore2(score, score2);
            Log.d(TAG, "updateScore_Dot2: updatedNum=" + updatedNum);

            hideProgressBar();
        }).start();
    }

    private void updateScore_List() {
        showProgressBar();
        new Thread(() -> {
            Score score = new Score();
            score.courseId = 1;
            score.stuId = 1;
            score.score = 50;

            Score score2 = new Score();
            score2.courseId = 1;
            score2.stuId = 2;
            score2.score = 51;

            List<Score> list = new ArrayList<>();
            list.add(score);
            list.add(score2);

            // TODO: 2019/4/4 side effect：modify temp column
            int updatedNum = teachDao.updateScore(list);
            Log.d(TAG, "updateScore_Dot2: updatedNum=" + updatedNum);

            hideProgressBar();
        }).start();
    }

    private void updateScore_Query() {
        showProgressBar();
        new Thread(() -> {
            int updatedNum = teachDao.updateScore(50, 1, 1);
            Log.d(TAG, "updateScore_Dot2: updatedNum=" + updatedNum);

            hideProgressBar();
        }).start();
    }

    private void queryCourseAll() {
        showProgressBar();
        new Thread(() -> {
            List<Course> courseList = teachDao.getCourseAll();

            String queryResult = mMock.printCourse(courseList);
            printResult(queryResult);
        }).start();
    }

    private void queryStudentAll() {
        showProgressBar();
        new Thread(() -> {
            List<Student> list = teachDao.getStudentAll();

            String queryResult = mMock.printStudent(list);
            printResult(queryResult);
        }).start();
    }

    private void findStudentUseName() {
        showProgressBar();
        new Thread(() -> {
            List<Student> list = teachDao.findStudentUseName("S", "Harvard");

            String queryResult = mMock.printStudent(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScoreAll() {
        showProgressBar();
        new Thread(() -> {
            List<Score> list = teachDao.getScoreAll();

            String queryResult = mMock.printScore(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_bigThan() {
        showProgressBar();
        new Thread(() -> {
            List<Score> list = teachDao.getScores(90);

            String queryResult = mMock.printScore(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_BetweenAnd() {
        showProgressBar();
        new Thread(() -> {
            Score[] array = teachDao.getScores(60, 90);

            String queryResult = mMock.printScore(array);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_In() {
        showProgressBar();
        new Thread(() -> {
            int[] courseIds = new int[]{2, 5};

            List<ScoreTuple> list = teachDao.getScores(courseIds);

            String queryResult = mMock.printScoreTuple(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_Subsets() {
        showProgressBar();
        new Thread(() -> {
            List<ScoreTuple> list = teachDao.getScores();

            String queryResult = mMock.printScoreTuple(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_Limit() {
        showProgressBar();
        new Thread(() -> {
            Score score = teachDao.getScore(2, 2);

            String queryResult = mMock.printScore(score);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_LimitV2() {
        showProgressBar();
        new Thread(() -> {
            List<Score> list = teachDao.getScore2(2, 2);

            String queryResult = mMock.printScore(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScore_Cursor() {
        showProgressBar();
        new Thread(() -> {
            Cursor cursor = teachDao.loadScoresGreaterThan(80);

            List<Score> list = mMock.cursor2BeanList(cursor);
            if (null != cursor) {
                cursor.close();
            }

            String queryResult = mMock.printScore(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScores_Inner_Join() {
        showProgressBar();
        new Thread(() -> {
            List<ScoreStuCourseTuple> list = teachDao.findScoreGreatThan(90);

            String queryResult = mMock.printScore2(list);
            printResult(queryResult);
        }).start();
    }

    private void queryScores_MultiTableAnd() {
        showProgressBar();
        new Thread(() -> {
            List<ScoreStuCourseTuple> list = teachDao.findScoreGreatThan2(90);

            String queryResult = mMock.printScore2(list);
            printResult(queryResult);
        }).start();
    }

    private void printResult(String queryResult) {
        runOnUiThread(() -> {
            mQueryResultView.setText(queryResult);
            hideProgressBar2();
        });
    }

    private void showProgressBar() {
        mProgressBarLayout.setVisibility(View.VISIBLE);
        mBtnLayout.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        runOnUiThread(() -> {
            mProgressBarLayout.setVisibility(View.GONE);
            mBtnLayout.setVisibility(View.VISIBLE);
        });
    }

    private void hideProgressBar2() {
        mProgressBarLayout.setVisibility(View.GONE);
        mBtnLayout.setVisibility(View.VISIBLE);
    }

    private void showToast(final String msg) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show());
    }
}