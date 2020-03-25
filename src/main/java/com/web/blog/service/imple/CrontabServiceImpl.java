package com.web.blog.service.imple;


import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务
 * @author xiaoming
 * @date 2018/8/23 13:33
 */

public class CrontabServiceImpl {

//    @Autowired
//    @Autowired
//    private ScoreDetailDao scoreDetailDao;
    /**
     * 备份学生数据
     * @author ChenZhiMing
     * @date 2018/8/23 20:17
     **/
    /*@Scheduled(cron = "0 0 3 * * *") // 每天03:00执行
    public void studentInfo() {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
        List<Student> studentList = studentDao.getList();
        ExcelIO.writeExcelByStudentList(studentList,
                FileIO.FILE_DOWNLOAD_FILE_PATH + "studentScore/",
                "studentScore" + ft.format(dNow) + ".xls");
    }*/

    /**
     * 备份操作记录
     * @author ChenZhiMing
     * @date 2018/8/25 16:50
     **/
    /*@Scheduled(cron = "0 30 3 * * *") // 每天03:30执行
    public void scoreDetail() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
        List<ScoreDetail> scoreDetailList = scoreDetailDao.getList();
        ExcelIO.writeExcelByScoreDetailList(scoreDetailList,
                FileIO.FILE_DOWNLOAD_FILE_PATH + "scoreDetail/",
                "scoreDetail" + ft.format(dNow) + ".xls");
    }*/
}
