import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/phmn";
    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "laifuyin123!";


    public static void insertItemsets(String itemset, int id, String table){
        Connection conn = null;
        Statement stmt = null;
        int count=0;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            //获取连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 创建会话
            stmt = conn.createStatement();
            String insertSql="";
            insertSql=insertItemset(id,itemset,table);
            count += stmt.execute(insertSql)?1:0;
//            }

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }


    }

    private static String insertItemset(int id, String itemset, String table) {
        StringBuffer insertSql=new StringBuffer();
        insertSql.append("INSERT INTO `phmn_improve`.`"+table+"` (`id`, `itemset`) ");
        insertSql.append( "VALUES (");
        insertSql.append(  "'"+id+"'"+","+
                           "'"+itemset+"'"  +");");
        String res=insertSql.toString();
        return res;
    }


    public void TRUNCATETable(String table,String dataset) {


        StringBuffer insertSql1=new StringBuffer();
        insertSql1.append("DELETE FROM `phmn_improve`.`");
        insertSql1.append(table);
        insertSql1.append("` WHERE (`dataset` = '");
        insertSql1.append(dataset);
        insertSql1.append("')");

        String res=insertSql1.toString();

        Connection conn = null;
        Statement stmt = null;
        int count=0;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            //获取连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 创建会话
            stmt = conn.createStatement();
            String insertSql="";
            insertSql=res;
            count += stmt.execute(insertSql)?1:0;
//            }

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    public static void insertThresAndRes(int id,String dataset, int minUtil, int minPer,
                                         int maxPer, int minAvg, int maxAvg,
                                         double time, double memory, int phuiCount,
                                         int candidateCount, String table){
        Connection conn = null;
        Statement stmt = null;
        int count=0;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            //获取连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 创建会话
            stmt = conn.createStatement();
            String insertSql="";
            insertSql=insertTAS(id,dataset, minUtil, minPer, maxPer, minAvg, maxAvg,
                    time, memory, phuiCount, candidateCount, table);
            count += stmt.execute(insertSql)?1:0;
//            }

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }


    }

    private static String insertTAS(int id, String dataset, int minUtil, int minPer, int maxPer, int minAvg, int maxAvg, double time, double memory, int phuiCount, int candidateCount, String table) {

        StringBuffer insertSql=new StringBuffer();
        insertSql.append("INSERT INTO `phmn_improve`.`"+table+"` (`id`, `dataset`,`minUtility`,`minPer`,`maxPer`,`minAvg`,`maxAvg`," +
                "`time`, `memory`, `itemsetCount`, `CandidateCount`) ");
        insertSql.append( "VALUES (");
        insertSql.append(  "'"+id+"'"+","+
                "'"+dataset+"'"+","+
                "'"+minUtil+"'"+","+
                "'"+minPer+"'"+","+
                "'"+maxPer+"'"+","+
                "'"+minAvg+"'"+","+
                "'"+maxAvg+"'"+","+
                "'"+time+"'"+","+
                "'"+memory+"'"+","+
                "'"+phuiCount+"'"+","+
                "'"+candidateCount+"'"  +");");
        String res=insertSql.toString();
        return res;

    }

}
