# ergo-openjdk18-binary
FROM openshift/base-centos7

# TODO: Put the maintainer name in the image metadata
LABEL maintainer="Torsten Kramer <torsten.kramer@nowhere.org>"

# TODO: Rename the builder environment variable to inform users about application you provide them
ENV OPENJDK_VERSION 1.8

# TODO: Set labels used in OpenShift to describe the builder image
LABEL io.k8s.description="Builder image for Java 8 applications" \
      io.k8s.display-name="OpenJDK 1.8" \
      io.openshift.expose-services="8080:http" \
      io.openshift.tags="builder,openjdk" \
      # this label tells s2i where to find its mandatory scripts
      # (run, assemble, save-artifacts)
      io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

# TODO: Install required packages here:
# RUN yum install -y ... && yum clean all -y
RUN yum install -y java-1.8.0-openjdk-headless && yum clean all -y

# TODO (optional): Copy the builder files into /opt/app-root
# COPY ./<builder_folder>/ /opt/app-root/

# TODO: Copy the S2I scripts to /usr/libexec/s2i, since openshift/base-centos7 image
# sets io.openshift.s2i.scripts-url label that way, or update that label
COPY ./s2i/bin/ /usr/libexec/s2i

# TODO: Drop the root user and make the content of /opt/app-root owned by user 1001
RUN chown -R 1001:1001 /opt/app-root

# This default user is created in the openshift/base-centos7 image
USER 1001

# TODO: Set the default port for applications built using this image
EXPOSE 8080

# TODO: Set the default CMD for the image
CMD ["/usr/libexec/s2i/usage"]
