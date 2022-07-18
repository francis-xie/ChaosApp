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
    routeInfos.put("/xrouter/service/autowired", RouteInfo.build(RouteType.PROVIDER, AutoWiredServiceImpl.class, "/xrouter/service/autowired", "xrouter", (Map) null, -1, Integer.MIN_VALUE));
    routeInfos.put("/xrouter/service/interceptor", RouteInfo.build(RouteType.PROVIDER, InterceptorServiceImpl.class, "/xrouter/service/interceptor", "xrouter", (Map) null, -1, Integer.MIN_VALUE));
  }
}
