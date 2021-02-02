package challenge.Model;

public enum AlbumPermissions {
    READ(0),WRITE(1);

    private int permission;

    private AlbumPermissions(int permission){
        this.permission=permission;
    }

    public int getCodePermission(){
        return this.permission;
    }

}
