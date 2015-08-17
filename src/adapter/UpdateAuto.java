package adapter;

/**
 * Created by santa on 6/11/15.
 */
public interface UpdateAuto {
    public void updateOptionSetName(String model, String optionsetname, String newname);
    public void updateOptionPrice(String modelname, String optionname, String option, float newprice);

}
