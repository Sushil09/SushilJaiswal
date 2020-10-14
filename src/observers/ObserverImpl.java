package observers;

public class ObserverImpl implements IObserver{

    @Override
    public void update(char type ,String path,String name) {
        switch (type){
            case 'c':   //for new node creation
                System.out.printf("The new node named %s is added at %s\n",name,path);
                break;
            case 'u':   //for updating node
                System.out.printf("The node named %s at path %s is updated \n",name,path);
                break;
            case 'd':   //for deleting node
                System.out.printf("The node named %s at path %s is deleted \n",name,path);
                break;
            default:
                System.out.println();
        }
    }
}
