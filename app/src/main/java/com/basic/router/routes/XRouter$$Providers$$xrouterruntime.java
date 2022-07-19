package com.basic.router.routes;

import com.basic.router.core.AutoWiredServiceImpl;
import com.basic.router.core.InterceptorServiceImpl;
import com.basic.router.enums.RouteType;
import com.basic.router.facade.template.IProviderGroup;
import com.basic.router.model.RouteInfo;
import java.util.Map;

public class XRouter$$Providers$$xrouterruntime implements IProviderGroup {
  public XRouter$$Providers$$xrouterruntime() {
  }

  public void loadInto(Map<String, RouteInfo> providers) {
    providers.put("com.basic.router.facade.service.AutoWiredService", RouteInfo.build(RouteType.PROVIDER, AutoWiredServiceImpl.class, "/router/service/autowired", "router", (Map)null, -1, Integer.MIN_VALUE));
    providers.put("com.basic.router.facade.service.InterceptorService", RouteInfo.build(RouteType.PROVIDER, InterceptorServiceImpl.class, "/router/service/interceptor", "router", (Map)null, -1, Integer.MIN_VALUE));
  }
}
