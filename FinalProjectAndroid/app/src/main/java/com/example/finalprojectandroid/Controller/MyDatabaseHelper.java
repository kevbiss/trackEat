package com.example.finalprojectandroid.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.time.LocalDate;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.time.LocalDate;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME ="trackEat.db";
    public static final int DATABASE_VERSION =1;

    public static final String  TABLE_UTLISATEUR ="Utilisateur";

    public static final String COLUMN_ID= "_idUtilisateur";
    public static final String COLUMN_NAME="prenom";
    public static final String COLUMN_LASTNAME ="nom";

    public static  final String TABLE_INFOPERSO ="info_perso";

    public static final String COLUMN_IDUTILISATEUR= "_idUtilisateur";
    public static final String COLUMN_ID1= "_id";
    public static final String COLUMN_MultiActivity ="multiplicateur_d_activite";
    public static final String COLUMN_TAILLE= "Taille";
    public static final String COLUMN_TAUXDEGRAISSE= "Taux_de_graisse";
    public static final String COLUMN_OPOIDS = "objectifPoids";
    public static final String COLUMN_OTAUXDEGRAISSE= "objectifTaux_de_graisse";


    public static  final String TABLE_SUIVIPOIDS ="suivi_poids";

    public static final String COLUMN_IDSuivit= "_idSuivit";
    public static final String COLUMN_IDUTILISATEUR1= "_idUtilisateur";
    public static final String COLUMN_DATE= "Date";
    public static final String COLUMN_ESTRECENT= "estRecent";
    public static final String COLUMN_MTAILLE= "taille";
    public static final String COLUMN_MPOIDS= "poids";
    public static final String COLUMN_COU= "tour_de_cou";
    public static final String COLUMN_POITRINE= "tour_de_poitrine";
    public static final String COLUMN_BRASGAUCHE= "tour_brasGauche";
    public static final String COLUMN_BRASDROIT= "tour_brasDroit";
    public static final String COLUMN_VENTRE= "tour_de_ventre";
    public static final String COLUMN_TOURTAILLE= "tour_de_taille";
    public static final String COLUMN_TOURDEFESSE= "tour_de_fesse";
    public static final String COLUMN_CUISSEGAUCHE= "tour_cuisseGauche";
    public static final String COLUMN_CUISSEDROITE= "tour_cuisseDroite";


    public static  final String TABLE_PLANALIM ="planAlim";

    public static final String COLUMN_ID_PLAN= "_idPlan";
    public static final String COLUMN_TYPE= "Type_de_plan";
    public static final String COLUMN_COEFF= "Coeff";
    public static final String COLUMN_DATEDEBUT= "Date_de_Debut";
    public static final String COLUMN_DATEFIN= "Date_de_Fin";
    public static final String COLUMN_ESTACTiF= "estActif";
    public static final String COLUMN_KCAL= "kcals";
    public static final String COLUMN_PROTEINE= "proteines";
    public static final String COLUMN_GLUCIDE= "Glucides";
    public static final String COLUMN_LIPIDE= "Lipides";


    public static  final String TABLE_ADMIN ="Admin";

    public static final String COLUMN_ID_ADMIN= "_idAdmin";
    public static final String COLUMN_NAMEADMIN="nom";
    public static final String COLUMN_LASTNAMEADMIN ="prenom";
    //public static final String COLUMN_ESTACTiF= "estActif";


    public static  final String TABLE_FAQ ="FAQ";

    public static final String COLUMN_ID_FAQ= "_idFAQ";
    public static final String COLUMN_Question= "questions";
    public static final String COLUMN_Reponse= "reponses";



    public static  final String TABLE_COMPTAGE ="comptage";

    //Column date
    public static final String COLUMN_ID_COMPTAGE= "_idComptage";
    public static final String COLUMN_KCALMax= "kcals_Max";
    public static final String COLUMN_PROTEINEMax= "proteines_Max";
    public static final String COLUMN_GLUCIDEMax= "Glucides_Max";
    public static final String COLUMN_LIPIDEMax= "Lipides_Max";

    public static  final String TABLE_Aliment ="ALIMENTS";

    public static final String COLUMN_ID_Aliments= "_idAliments";
    public static final String COLUMN_NameAliment= "nom";
    public static final String COLUMN_Quantite= "Quantite";






    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_UTLISATEUR +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME+ " TEXT," +
                COLUMN_LASTNAME + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_INFOPERSO +
        "(" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_IDUTILISATEUR+ " INTEGER ,"+
                COLUMN_MultiActivity +" DOUBLE," +
                COLUMN_TAILLE +" DOUBLE," +
                COLUMN_TAUXDEGRAISSE + " DOUBLE," +
                COLUMN_OPOIDS + " DOUBLE," +
                COLUMN_OTAUXDEGRAISSE + " DOUBLE," +
                "CONSTRAINT test FOREIGN KEY ("+COLUMN_IDUTILISATEUR+") REFERENCES my_library ("+COLUMN_ID+"));";

        String query3 = "CREATE TABLE " + TABLE_SUIVIPOIDS+
                "(" + COLUMN_IDSuivit + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_IDUTILISATEUR+ " INTEGER ,"+
                COLUMN_DATE +" DATE," +
                COLUMN_ESTRECENT +" BOOLEAN,"+
                COLUMN_MTAILLE +" DOUBLE," +
                COLUMN_MPOIDS+" DOUBLE," +
                COLUMN_TAUXDEGRAISSE+" DOUBLE," +
                COLUMN_COU+" DOUBLE," +
                COLUMN_POITRINE+" DOUBLE," +
                COLUMN_VENTRE+" DOUBLE," +
                COLUMN_BRASGAUCHE+" DOUBLE," +
                COLUMN_BRASDROIT+" DOUBLE," +
                COLUMN_TOURTAILLE+" DOUBLE," +
                COLUMN_TOURDEFESSE+" DOUBLE," +
                COLUMN_CUISSEDROITE+" DOUBLE," +
                COLUMN_CUISSEGAUCHE+" DOUBLE," +
                "CONSTRAINT test FOREIGN KEY ("+COLUMN_IDUTILISATEUR+") REFERENCES my_library (_id));";

        String query4 = "CREATE TABLE " + TABLE_PLANALIM+
                "(" + COLUMN_ID_PLAN + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_IDUTILISATEUR+ " INTEGER,"+
                COLUMN_TYPE +" INTEGER,"+
                COLUMN_COEFF +" DOUBLE,"+
                COLUMN_DATEDEBUT +" DATE," +
                COLUMN_DATEFIN +" DATE," +
                COLUMN_ESTACTiF +" BOOLEAN,"+

                COLUMN_KCAL +" DOUBLE," +
                COLUMN_PROTEINE+" DOUBLE," +
                COLUMN_GLUCIDE+" DOUBLE," +
                COLUMN_LIPIDE+" DOUBLE," +
                "CONSTRAINT test FOREIGN KEY ("+COLUMN_IDUTILISATEUR+") REFERENCES my_library (_id));";

        String query5 ="CREATE TABLE "+ TABLE_ADMIN +
                "("+ COLUMN_ID_ADMIN + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME+ " TEXT," +
                COLUMN_LASTNAME + " TEXT," +
                COLUMN_ESTACTiF +" BOOLEAN);";

        String query6 ="CREATE TABLE " +TABLE_FAQ+
                "("+ COLUMN_ID_FAQ + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ID_ADMIN+ " INTEGER,"+
                COLUMN_Question+ " varchar (50)," +
                COLUMN_Reponse+ " VARCHAR (50)," +
                "CONSTRAINT test1 FOREIGN KEY ("+COLUMN_ID_ADMIN+") REFERENCES "+ TABLE_ADMIN+ " ("+COLUMN_Reponse+"));";

        String query7 ="CREATE TABLE " + TABLE_COMPTAGE+
                "("+ COLUMN_ID_COMPTAGE + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_IDUTILISATEUR+ " INTEGER,"+
                COLUMN_DATE +" DATE," +
                COLUMN_ESTACTiF +" BOOLEAN,"+
                COLUMN_KCAL +" DOUBLE," +
                COLUMN_PROTEINE+" DOUBLE," +
                COLUMN_GLUCIDE+" DOUBLE," +
                COLUMN_LIPIDE+" DOUBLE," +
                COLUMN_KCALMax +" DOUBLE," +
                COLUMN_PROTEINEMax+" DOUBLE," +
                COLUMN_GLUCIDEMax+" DOUBLE," +
                COLUMN_LIPIDEMax+" DOUBLE," +
                "CONSTRAINT test FOREIGN KEY ("+COLUMN_IDUTILISATEUR+") REFERENCES my_library (_id));";

        String query8 ="CREATE TABLE " + TABLE_Aliment+
                "("+ COLUMN_ID_Aliments + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ID_COMPTAGE+ " INTEGER,"+
                COLUMN_NameAliment+ " varchar (50)," +
                COLUMN_Quantite +" DOUBLE," +
                COLUMN_KCAL +" DOUBLE," +
                COLUMN_PROTEINE+" DOUBLE," +
                COLUMN_GLUCIDE+" DOUBLE," +
                COLUMN_LIPIDE+" DOUBLE," +
                "CONSTRAINT test FOREIGN KEY ("+COLUMN_ID_COMPTAGE+") REFERENCES comptage (_idComptage));";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTLISATEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFOPERSO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUIVIPOIDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANALIM);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_FAQ);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_Aliment);
        onCreate(db);

    }

    void addUtilisateur(String nom , String prenom){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,nom);
        cv.put(COLUMN_LASTNAME,prenom);

        long result = db.insert(TABLE_UTLISATEUR,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled add Utilisateur",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Utilisateur Succesfully",Toast.LENGTH_SHORT).show();
        }

    }

    void addInfoperso(int id,double mlt,double taille, double oPoids, double oTXD,double TXD){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_IDUTILISATEUR,id);
        cv.put(COLUMN_MultiActivity,mlt);
        cv.put(COLUMN_TAILLE,taille);
        cv.put(COLUMN_OPOIDS,oPoids);
        cv.put(COLUMN_OTAUXDEGRAISSE,oTXD);
        cv.put(COLUMN_TAUXDEGRAISSE,TXD);

        long result = db.insert(TABLE_INFOPERSO,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }

    }


    void addSuivitPoid(  int id,Double taille, Double poids ,Double pcrtMasseGraisseuse, Double cou , Double poitrine , Double ventre, Double brasG, Double brasD, Double Ttaille, Double fesse, Double cuisseD, Double cuisseG){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        Boolean flag =true;


        cv.put(COLUMN_IDUTILISATEUR1,id);
        cv.put(COLUMN_DATE, String.valueOf(date));
        cv.put(COLUMN_ESTRECENT,flag);
        cv.put(COLUMN_MTAILLE,taille);
        cv.put(COLUMN_MPOIDS,poids);
        cv.put(COLUMN_TAUXDEGRAISSE,pcrtMasseGraisseuse/100);
        cv.put(COLUMN_COU,cou);
        cv.put(COLUMN_POITRINE,poitrine);
        cv.put(COLUMN_VENTRE,ventre);
        cv.put(COLUMN_BRASGAUCHE,brasG);
        cv.put(COLUMN_BRASDROIT,brasD);
        cv.put(COLUMN_TOURTAILLE,Ttaille);
        cv.put(COLUMN_TOURDEFESSE,fesse);
        cv.put(COLUMN_CUISSEDROITE,cuisseD);
        cv.put(COLUMN_CUISSEGAUCHE,cuisseG);


        long result = db.insert(TABLE_SUIVIPOIDS,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled Suivi Poids",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }
    }


    void addPlanAlim( int id,int type, Double coeff,Double kcal,Double proteines,double glucides,double lipide){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        LocalDate dateDebutJava,dateFinJava;
        dateDebutJava=LocalDate.now();
        dateFinJava=dateDebutJava.plusWeeks(10);

        java.sql.Date dateDebutSQL,dateFinSQL;

        dateDebutSQL =java.sql.Date.valueOf(String.valueOf(dateDebutJava));
        dateFinSQL=java.sql.Date.valueOf(String.valueOf(dateFinJava));


        Boolean flag =true;


        cv.put(COLUMN_IDUTILISATEUR1,id);
        cv.put(COLUMN_TYPE,type);
        cv.put(COLUMN_COEFF,coeff);
        cv.put(COLUMN_DATEDEBUT, String.valueOf(dateDebutSQL));
        cv.put(COLUMN_DATEFIN, String.valueOf(dateFinSQL));
        cv.put(COLUMN_ESTACTiF,flag);


        cv.put(COLUMN_KCAL,kcal);
        cv.put(COLUMN_PROTEINE,proteines);
        cv.put(COLUMN_GLUCIDE,glucides);
        cv.put(COLUMN_LIPIDE,lipide);


        long result = db.insert(TABLE_PLANALIM,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled PlanAlim",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully PlanALim",Toast.LENGTH_SHORT).show();
        }
    }

    void addAdmin(String prenom, String nom){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMEADMIN,nom);
        cv.put(COLUMN_LASTNAMEADMIN,prenom);
        cv.put(COLUMN_ESTACTiF,true);

        long result = db.insert(TABLE_ADMIN,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled Suivi Poids",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }

    }

    void addQuestion(int id,String question,String reponse){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_ADMIN,id);
        cv.put(COLUMN_Question,question);
        cv.put(COLUMN_Reponse,reponse);

        long result = db.insert(TABLE_FAQ,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled Suivi Poids",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }


    }

    void addAliment(int id,String name,Double quantite,Double kcal,Double proteines,double glucides,double lipide){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();


      //  cv.put(COLUMN_ID_Aliments,id);
        cv.put(COLUMN_ID_COMPTAGE,id);
        cv.put(COLUMN_NameAliment,name);
        cv.put(COLUMN_Quantite,quantite);
        cv.put(COLUMN_KCAL ,kcal);
        cv.put(COLUMN_PROTEINE,proteines);
        cv.put(COLUMN_GLUCIDE,glucides);
        cv.put(COLUMN_LIPIDE,lipide);

        long result = db.insert(TABLE_Aliment,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled add Aliment",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }



    }


    void addComptage(int idU,Double kcalMax, Double pMax,double gMax , double lMax ){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        LocalDate dateDebutJava,dateFinJava;
        dateDebutJava=LocalDate.now();
        java.sql.Date dateDebutSQL;
        dateDebutSQL =java.sql.Date.valueOf(String.valueOf(dateDebutJava));

        Boolean flag =true;


        //cv.put(COLUMN_ID_COMPTAGE,id);
        cv.put(COLUMN_IDUTILISATEUR,idU);
        cv.put(COLUMN_DATE, String.valueOf(dateDebutSQL));
        cv.put(COLUMN_ESTACTiF,flag);

        cv.put(COLUMN_KCAL,0);
        cv.put(COLUMN_PROTEINE,0);
        cv.put(COLUMN_GLUCIDE,0);
        cv.put(COLUMN_LIPIDE,0);
        cv.put(COLUMN_KCALMax,kcalMax);
        cv.put(COLUMN_LIPIDEMax,lMax);
        cv.put(COLUMN_GLUCIDEMax,gMax);
        cv.put(COLUMN_PROTEINEMax,pMax);

        long result = db.insert(TABLE_COMPTAGE,null,cv);

        if (result ==1){
            Toast.makeText(context, "Failled Comptage",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Add Succesfully",Toast.LENGTH_SHORT).show();
        }




    }

    void modifieComptage(int id,Double kcal, Double proteine, Double glucide,Double lipide){

        String[] ls = new String[4];
        Cursor cursor = readComptageUTI(id);
        if (cursor.getCount() == 0){
        }
        else {
            while (cursor.moveToNext()){
                ls[0]=cursor.getString(4);
                ls[1]=cursor.getString(5);
                ls[2]=cursor.getString(6);
                ls[3]=cursor.getString(7);

            }
        }

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        readComptage(id);

        String query = "UPDATE "+ TABLE_COMPTAGE + " SET kcals =" + (kcal +Double.valueOf(ls[0])) +", " +
                "proteines = " +(proteine+Double.valueOf(ls[1])) + ", "+
                "Glucides = " + (glucide+Double.valueOf(ls[2])) + ", "+
                "Lipides = " + (lipide+Double.valueOf(ls[3])) +
                " WHERE "+ COLUMN_ID_COMPTAGE +" = " + id;

        db.execSQL(query);
    }

    String[] readComptageUti(int id){
        String[] ls = new String[4];
        Cursor cursor = readComptage(id);
        if (cursor.getCount() == 0){
        }
        else {
            while (cursor.moveToNext()){
                ls[0]=cursor.getString(4);
                ls[1]=cursor.getString(5);
                ls[2]=cursor.getString(6);
                ls[3]=cursor.getString(7);

            }
        }

        return ls ;
    }




    Cursor readAllData(){
        String query ="SELECT * FROM " + TABLE_UTLISATEUR;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readSuivi(){
        String query ="SELECT * FROM " + TABLE_SUIVIPOIDS +" where " + COLUMN_IDUTILISATEUR+ " =1";
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readAdmin(){
        String query ="SELECT * FROM " + TABLE_ADMIN +" where " + COLUMN_ID_ADMIN+ " =1";
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readFAQ(){
        String query ="SELECT * FROM " + TABLE_FAQ;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readPlanAlimUtilisateur(int id){
        String query ="SELECT * FROM planAlim" +" where " + COLUMN_IDUTILISATEUR+ " ="+id;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readPlanAlim(int id){
        String query ="SELECT * FROM planAlim" + " where " + COLUMN_IDUTILISATEUR+ " ="+id;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readComptage(int id){
        String query ="SELECT * FROM comptage"+  "  where " + COLUMN_IDUTILISATEUR+ " ="+id;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readComptageUTI(int id){
        String query ="SELECT * FROM comptage"+  "  where " + COLUMN_ID_COMPTAGE+ " ="+id;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }


    Cursor readAliments(){
        String query ="SELECT * FROM ALIMENTS";
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }

    Cursor readInfoPerso(int id){
        String query ="SELECT * FROM info_perso" + " where " + COLUMN_IDUTILISATEUR+ " ="+id;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }






    Cursor readDataUti(){
       // String query ="SELECT * FROM " + TABLE_UTLISATEUR  +" Where _id=1 ";
        String query ="SELECT * FROM " + TABLE_UTLISATEUR  +" Where _id=1 ";
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =null;
        if (db !=null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }



}

