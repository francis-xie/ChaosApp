package com.basic.router.routes;

import com.basic.router.core.AutoWiredServiceImpl;
import com.basic.router.core.InterceptorServiceImpl;
import com.basic.router.enums.RouteType;
import com.basic.router.facade.template.IRouteGroup;
import com.basic.router.model.RouteInfo;
import java.util.Map;

public class XRouter$$Group$$xrouter implements IRouteGroup {
  public XRouter$$Group$$xrouter() {
  }

  public void loadInto(Map<String, RouteInfo> routeInfos) {
    routeInfos.put("/router/service/autowired", RouteInfo.build(RouteType.PROVIDER, AutoWiredServiceImpl.class, "/router/service/autowired", "router", (Map)null, -1, Integer.MIN_VALUE));
    routeInfos.put("/router/service/interceptor", RouteInfo.build(RouteType.PROVIDER, InterceptorServiceImpl.class, "/router/service/interceptor", "router", (Map)null, -1, Integer.MIN_VALUE));
  }
}
