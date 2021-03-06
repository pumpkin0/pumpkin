
package zhangbin.cumt.item_bos.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.take_delivery.Order;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderService", targetNamespace = "http://take_delivery.service.cumt.zhangbin/")
@XmlSeeAlso({
    //ObjectFactory.class
})
public interface OrderService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateByManual", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.UpdateByManual")
    @ResponseWrapper(localName = "updateByManualResponse", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.UpdateByManualResponse")
    public void updateByManual(
        @WebParam(name = "arg0", targetNamespace = "")
        Order arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns zhangbin.cumt.service.take_delivery.PageImpl
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pageQuery", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.PageQuery")
    @ResponseWrapper(localName = "pageQueryResponse", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.PageQueryResponse")
    public PageImpl pageQuery(
        @WebParam(name = "arg0", targetNamespace = "")
        PageRequest arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<zhangbin.cumt.service.take_delivery.Courier>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "assignCourierByManual", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.AssignCourierByManual")
    @ResponseWrapper(localName = "assignCourierByManualResponse", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.AssignCourierByManualResponse")
    public List<Courier> assignCourierByManual(
        @WebParam(name = "arg0", targetNamespace = "")
        Order arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Area arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "save", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://take_delivery.service.cumt.zhangbin/", className = "zhangbin.cumt.service.take_delivery.SaveResponse")
    public void save(
        @WebParam(name = "arg0", targetNamespace = "")
        Order arg0);

}
