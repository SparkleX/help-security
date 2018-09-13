package help;

import java.security.Provider;
import java.security.Security;

import org.joda.time.DateTime;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.messaging.encoder.MessageEncodingException;
import org.opensaml.saml.common.messaging.context.SAMLEndpointContext;
import org.opensaml.saml.common.messaging.context.SAMLPeerEntityContext;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.binding.encoding.impl.HTTPRedirectDeflateEncoder;
import org.opensaml.saml.saml2.core.AuthnContext;
import org.opensaml.saml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml.saml2.core.AuthnContextComparisonTypeEnumeration;
import org.opensaml.saml.saml2.core.AuthnRequest;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.saml.saml2.core.NameIDPolicy;
import org.opensaml.saml.saml2.core.NameIDType;
import org.opensaml.saml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml.saml2.metadata.Endpoint;
import org.opensaml.saml.saml2.metadata.SingleSignOnService;
import org.opensaml.xmlsec.SignatureSigningParameters;
import org.opensaml.xmlsec.config.JavaCryptoValidationInitializer;
import org.opensaml.xmlsec.context.SecurityParametersContext;
import org.opensaml.xmlsec.signature.support.SignatureConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//https://www.jianshu.com/p/d041935641b4
	
public class AppAuthnRequest {
	private static Logger logger = LoggerFactory.getLogger(AppAuthnRequest.class);
	public static void main(String[] args) throws Exception 
	{
		JavaCryptoValidationInitializer javaCryptoValidationInitializer =new JavaCryptoValidationInitializer();
		javaCryptoValidationInitializer.init();
        for (Provider jceProvider : Security.getProviders()) {
            logger.info(jceProvider.getInfo());
        }
        InitializationService.initialize();
        AuthnRequest authnRequest = buildAuthnRequest();
        OpenSAMLUtils.logSAMLObject(authnRequest);
        
        
        
        MessageContext context = new MessageContext();

        context.setMessage(authnRequest);

        //关于传输对端实体的信息，对于IDP就是SP，对于SP就是IDP；
        SAMLPeerEntityContext peerEntityContext =
                context.getSubcontext(SAMLPeerEntityContext.class, true);

        //端点信息；
        SAMLEndpointContext endpointContext =
                peerEntityContext.getSubcontext(SAMLEndpointContext.class, true);
        endpointContext.setEndpoint(getIPDEndpoint());

        //数据签名环境上线文
        SignatureSigningParameters signatureSigningParameters = new SignatureSigningParameters();
        //获得证书，其中包含公钥
        signatureSigningParameters.setSigningCredential(SPCredentials.getCredential());
        //ALGO_ID_SIGNATURE_RSA_SHA256
        signatureSigningParameters.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);


        context.getSubcontext(SecurityParametersContext.class, true)
                .setSignatureSigningParameters(signatureSigningParameters);

        // OpenSAML提供了HTTPRedirectDefalteEncoder
        // 它将帮助我们来对于AuthnRequest进行序列化和签名
        HTTPRedirectDeflateEncoder encoder = new HTTPRedirectDeflateEncoder();

        encoder.setMessageContext(context);
       // encoder.setHttpServletResponse(httpServletResponse);

          encoder.initialize();
        logger.info("Redirecting to IDP");
            encoder.encode();
       
	}
	
	
    private static Endpoint getIPDEndpoint() {
        SingleSignOnService endpoint = OpenSAMLUtils.buildSAMLObject(SingleSignOnService.class);
        endpoint.setBinding(SAMLConstants.SAML2_REDIRECT_BINDING_URI);
        endpoint.setLocation("http://localhost:8080/webprofile-ref-project/idp/singleSignOnService");

        return endpoint;
    }
    
    private static AuthnRequest buildAuthnRequest() {
        AuthnRequest authnRequest = OpenSAMLUtils.buildSAMLObject(AuthnRequest.class);
        //请求时间：该对象创建的时间，以判断其时效性
        authnRequest.setIssueInstant(new DateTime());
        //目标URL：目标地址，IDP地址
        authnRequest.setDestination("http://localhost:8080/webprofile-ref-project/idp/singleSignOnService");
        //传输SAML断言所需要的绑定：也就是用何种协议使用Artifact来取回真正的认证信息，
        authnRequest.setProtocolBinding(SAMLConstants.SAML2_ARTIFACT_BINDING_URI);
        //SP地址： 也就是SAML断言返回的地址
        authnRequest.setAssertionConsumerServiceURL("http://localhost:8080/webprofile-ref-project/sp/consumer");
        //请求的ID：为当前请求设置ID，一般为随机数
        authnRequest.setID(OpenSAMLUtils.generateSecureRandomId());
        //Issuer： 发行人信息，也就是SP的ID，一般是SP的URL
        authnRequest.setIssuer(buildIssuer());
        //NameID：IDP对于用户身份的标识；NameID policy是SP关于NameID是如何创建的说明
        authnRequest.setNameIDPolicy(buildNameIdPolicy());
        // 请求认证上下文（requested Authentication Context）:
        // SP对于认证的要求，包含SP希望IDP如何验证用户，也就是IDP要依据什么来验证用户身份。
        authnRequest.setRequestedAuthnContext(buildRequestedAuthnContext());

        return authnRequest;
    }
    private static Issuer buildIssuer() {
        Issuer issuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        issuer.setValue("TestSP");

        return issuer;
    }
    private static RequestedAuthnContext buildRequestedAuthnContext() {
        RequestedAuthnContext requestedAuthnContext = OpenSAMLUtils.buildSAMLObject(RequestedAuthnContext.class);
        requestedAuthnContext.setComparison(AuthnContextComparisonTypeEnumeration.MINIMUM);

        AuthnContextClassRef passwordAuthnContextClassRef = OpenSAMLUtils.buildSAMLObject(AuthnContextClassRef.class);
        passwordAuthnContextClassRef.setAuthnContextClassRef(AuthnContext.PASSWORD_AUTHN_CTX);

        requestedAuthnContext.getAuthnContextClassRefs().add(passwordAuthnContextClassRef);

        return requestedAuthnContext;

    }
    
    
    private static NameIDPolicy buildNameIdPolicy() {
        NameIDPolicy nameIDPolicy = OpenSAMLUtils.buildSAMLObject(NameIDPolicy.class);
        nameIDPolicy.setAllowCreate(true);

        nameIDPolicy.setFormat(NameIDType.TRANSIENT);

        return nameIDPolicy;
    }
}
