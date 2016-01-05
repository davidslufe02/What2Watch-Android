package com.example.user.testiguandroid.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.user.testiguandroid.Logica.Pelicula;


public class MyDataSource {

    //Variables para manipulación de datos
    private SQLiteDatabase database;
    private MyDBHelper dbHelper;

    public MyDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new MyDBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Metodo genérico para filtrar de cualquier tabla
     * Este método te ayuda a añadir todas las partes posibles de las cuales se podría componer una
     * consulta, además que te protege de inyecciones SQL, separando las clausulas de los argumentos.
     *
     * @param table         Nombre de la tabla a consultar
     * @param columns       Lista de nombres de las columnas que se van a consultar. Si deseas
     *                      obtener todas las columnas usas null.
     * @param selection     Es el cuerpo de la sentencia WHERE con las columnas a condicionar. Es
     *                      posible usar el placeholder ‘?’ para generalizar la condición.
     * @param selectionArgs Es una lista de los valores que se usaran para reemplazar las incógnitas
     *                      de selection en el WHERE.
     * @param groupBy       Aquí puedes establecer como se vería la clausula GROUP BY, si es que la
     *                      necesitas.
     * @param having        Establece la sentencia HAVING para condicionar a groupBy.
     * @param orderBy       Reordena las filas de la consulta a través de ORDER BY.
     * @return Devuelve un cursor con la consulta
     */
    public Cursor getAnyRow(String table, String[] columns, String selection,
                            String[] selectionArgs, String groupBy, String having, String orderBy) {
        return database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * Metodo para borrar filas de cualquier tabla
     *
     * @param table         Nombre de la tabla a consultar
     * @param selection     Es el cuerpo de la sentencia WHERE con las columnas a condicionar. Es
     *                      posible usar el placeholder ‘?’ para generalizar la condición.
     * @param selectionArgs Es una lista de los valores que se usaran para reemplazar las incógnitas
     *                      de selection en el WHERE.
     */
    public void deleteAnyRow(String table, String selection, String[] selectionArgs) {
        database.delete(table, selection, selectionArgs);
    }

    //******************************* MOVIE ********************************************

    //Metainformación de la base de datos
    public static final String MOVIE_TABLE_NAME = "Movie";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla Movie
    public static class ColumnMovie {
        public static final String ID_MOVIE = BaseColumns._ID;
        public static final String USER_MOVIE = "user";
        public static final String RATING_MOVIE = "rating";
        public static final String COMMENT_MOVIE = "comment";
        public static final String TITLE_MOVIE = "title";
        public static final String GENRE_MOVIE = "genre";
        public static final String PLOT_MOVIE = "plot";
        public static final String YEAR_MOVIE = "year";
        public static final String RATED_MOVIE = "rated";
        public static final String RELEASED_MOVIE = "released";
        public static final String DURATION_MOVIE = "duration";
        public static final String DIRECTOR_MOVIE = "director";
        public static final String WRITER_MOVIE = "writer";
        public static final String ACTORS_MOVIE = "actor";
        public static final String AWARDS_MOVIE = "awards";
        public static final String COUNTRY_MOVIE = "country";


    }

    //Script de Creación de la tabla Movie
    public static final String CREATE_MOVIE_SCRIPT =
            "create table " + MOVIE_TABLE_NAME + "(" +
                    ColumnMovie.ID_MOVIE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnMovie.USER_MOVIE + " " + STRING_TYPE + " not null," +
                    ColumnMovie.RATING_MOVIE + " " + INT_TYPE + "," +
                    ColumnMovie.COMMENT_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.TITLE_MOVIE + " " + STRING_TYPE + " not null," +
                    ColumnMovie.GENRE_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.PLOT_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.YEAR_MOVIE + " " + INT_TYPE + "," +
                    ColumnMovie.RATED_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.RELEASED_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.DURATION_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.DIRECTOR_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.WRITER_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.ACTORS_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.AWARDS_MOVIE + " " + STRING_TYPE + "," +
                    ColumnMovie.COUNTRY_MOVIE + " " + STRING_TYPE + ")";


    //Script para borrar la base de datos
    public static final String DROP_MOVIE_SCRIPT = "DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME;

    //Metodo para añadir una pelicula
    public void saveMovieRow(String user, int rating, String comment, Pelicula p) {
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando columnas
        values.put(ColumnMovie.USER_MOVIE, user);
        values.put(ColumnMovie.RATING_MOVIE, rating);
        values.put(ColumnMovie.COMMENT_MOVIE, comment);
        values.put(ColumnMovie.TITLE_MOVIE, p.getTitle());
        values.put(ColumnMovie.GENRE_MOVIE, p.getGenre());
        values.put(ColumnMovie.PLOT_MOVIE, p.getPlot());
        values.put(ColumnMovie.YEAR_MOVIE, p.getYear());
        values.put(ColumnMovie.RATED_MOVIE, p.getRated());
        values.put(ColumnMovie.RELEASED_MOVIE, p.getReleased());
        values.put(ColumnMovie.DURATION_MOVIE, p.getRuntime());
        values.put(ColumnMovie.DIRECTOR_MOVIE, p.getDirector());
        values.put(ColumnMovie.WRITER_MOVIE, p.getWriter());
        values.put(ColumnMovie.ACTORS_MOVIE, p.getActors());
        values.put(ColumnMovie.AWARDS_MOVIE, p.getAwards());
        values.put(ColumnMovie.COUNTRY_MOVIE, p.getCountry());

        //Insertando en la base de datos
        database.insert(MOVIE_TABLE_NAME, null, values);
    }


    //Devuelve todas las peliculas
    public Cursor getAllMovies() {
        //Seleccionamos todas las filas de la tabla Movie
        return database.rawQuery(
                "select * from " + MOVIE_TABLE_NAME, null);
    }

    public void actualizarMovie(int id, int rating, String comment){
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando body y author
        values.put(ColumnMovie.RATING_MOVIE, rating);
        values.put(ColumnMovie.COMMENT_MOVIE, comment);

        //Clausula WHERE
        String selection = ColumnMovie.ID_MOVIE + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        //Actualizando
        database.update(MOVIE_TABLE_NAME, values, selection, selectionArgs);
    }

    //obtener una peli por su id
    public Cursor getMovie(int id){
        String selection = ColumnMovie.ID_MOVIE + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        return getAnyRow(MOVIE_TABLE_NAME,null,selection,selectionArgs,null,null,null);
    }

    //borrar una peli por su id
    public void removeMovie(int id){
        String selection = ColumnMovie.ID_MOVIE + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        deleteAnyRow(MOVIE_TABLE_NAME,selection,selectionArgs);
    }


    //************************************ LIST *****************************************

    //Metainformación de la base de datos
    public static final String LIST_TABLE_NAME = "List";

    //Campos de la tabla List
    public static class ColumnList {
        public static final String ID_LIST = BaseColumns._ID;
        public static final String NAME_LIST = "name";
        public static final String DESCRIPTION_LIST = "description";


    }

    //Script de Creación de la tabla List
    public static final String CREATE_LIST_SCRIPT =
            "create table " + LIST_TABLE_NAME + "(" +
                    ColumnList.ID_LIST + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnList.NAME_LIST + " " + STRING_TYPE + " not null," +
                    ColumnList.DESCRIPTION_LIST + " " + STRING_TYPE + ")";


    //Script para borrar la base de datos
    public static final String DROP_LIST_SCRIPT = "DROP TABLE IF EXISTS " + LIST_TABLE_NAME;

    //Metodo para añadir una pelicula
    public void saveListRow(String name, String descripcion) {
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando columnas
        values.put(ColumnList.NAME_LIST, name);
        values.put(ColumnList.DESCRIPTION_LIST, descripcion);

        //Insertando en la base de datos
        database.insert(LIST_TABLE_NAME, null, values);
    }


    //Devuelve todas las listas
    public Cursor getAllLists() {
        //Seleccionamos todas las filas de la tabla List
        return database.rawQuery(
                "select * from " + LIST_TABLE_NAME, null);
    }

    public void actualizarList(int id, String descripcion){
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando body y author
        values.put(ColumnList.DESCRIPTION_LIST, descripcion);

        //Clausula WHERE
        String selection = ColumnList.ID_LIST + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        //Actualizando
        database.update(LIST_TABLE_NAME, values, selection, selectionArgs);
    }

    //obtener una lista por su id
    public Cursor getList(int id){
        String selection = ColumnList.ID_LIST + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        return getAnyRow(LIST_TABLE_NAME,null,selection,selectionArgs,null,null,null);
    }

    //borrar una lista por su id
    public void removeList(int id){
        String selection = ColumnList.ID_LIST + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        deleteAnyRow(LIST_TABLE_NAME,selection,selectionArgs);
    }


    //************************************** MoviesList ***********************************

    //Metainformación de la base de datos
    public static final String MOVIESLIST_TABLE_NAME = "MoviesList";

    //Campos de la tabla MoviesList
    public static class ColumnMoviesList {
        public static final String ID_MOVIE_MOVIESLIST = "idMovie";
        public static final String ID_LIST_MOVIESLIST = "idList";


    }

    //Script de Creación de la tabla MoviesList
    public static final String CREATE_MOVIESLIST_SCRIPT =
            "create table " + MOVIESLIST_TABLE_NAME + "(" +
                    ColumnMoviesList.ID_MOVIE_MOVIESLIST + " " + INT_TYPE + "," +
                    ColumnMoviesList.ID_LIST_MOVIESLIST + " " + INT_TYPE + ")";


    //Script para borrar la base de datos
    public static final String DROP_MOVIESLIST_SCRIPT = "DROP TABLE IF EXISTS " + MOVIESLIST_TABLE_NAME;

    //Metodo para añadir una pelicula a una lista
    public void saveListRow(int idMovie, int idList) {
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando columnas
        values.put(ColumnMoviesList.ID_MOVIE_MOVIESLIST, idMovie);
        values.put(ColumnMoviesList.ID_LIST_MOVIESLIST, idList);

        //Insertando en la base de datos
        database.insert(MOVIESLIST_TABLE_NAME, null, values);
    }

    //obtener las peliculas de una lista
    public Cursor getMoviesInList(int id){
        String selection = ColumnMoviesList.ID_LIST_MOVIESLIST + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        String columns[] = new String[]{ColumnMoviesList.ID_MOVIE_MOVIESLIST};
        return getAnyRow(MOVIESLIST_TABLE_NAME,columns,selection,selectionArgs,null,null,null);
    }

    //borrar todas las peliculas de una lista
    public void removeAllMoviesInList(int id){
        String selection = ColumnMoviesList.ID_LIST_MOVIESLIST + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        deleteAnyRow(MOVIESLIST_TABLE_NAME,selection,selectionArgs);
    }

    //borrar una movie de una lista
    public void removeMovieInList(int idMovie, int idList){
        String selection = ColumnMoviesList.ID_LIST_MOVIESLIST + " = ?   AND " +
                ColumnMoviesList.ID_MOVIE_MOVIESLIST + " = ?";
        String[] selectionArgs = { Integer.toString(idList),Integer.toString(idMovie) };

        deleteAnyRow(MOVIESLIST_TABLE_NAME,selection,selectionArgs);
    }

}