package ViewsControllers;

public abstract class InnerController {

    protected OuterController outerController;

    public OuterController getOuterController() {
        return outerController;
    }

    public void setOuterController(OuterController outerController) {
        this.outerController = outerController;
    }
}
