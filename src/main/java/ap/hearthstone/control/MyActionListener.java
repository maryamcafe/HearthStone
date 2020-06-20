package ap.hearthstone.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    private Request request;

    public MyActionListener(Request request){
        this.request = request;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Admin.getInstance().addRequest(request);
    }

    public Request getRequest() {
        return request;
    }
}
