package com.basic.router.routes;

import com.basic.router.facade.template.IRouteGroup;
import com.basic.router.facade.template.IRouteRoot;
import java.util.Map;

public class XRouter$$Root$$xrouterruntime implements IRouteRoot {
  @Override
  public void loadInto(Map<String, Class<? extends IRouteGroup>> routeGroups) {
    routeGroups.put("router", XRouter$$Group$$xrouter.class);
  }
}
