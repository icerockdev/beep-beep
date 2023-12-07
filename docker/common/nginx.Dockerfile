ARG NGINX_BUILD_IMAGE

FROM $NGINX_BUILD_IMAGE as runner

COPY ./docker/common/nginx.conf.tmpl /etc/nginx/templates/default.conf.tmpl

VOLUME ["/etc/letsencrypt"]
