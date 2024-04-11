FROM fedora:39

RUN echo "fastestmirror=1" >> /etc/dnf/dnf.conf
RUN dnf -y install make gcc
COPY ./byaccj-p2/src /src
WORKDIR /src
RUN make

FROM fedora:39
COPY --from=0 /src/yacc /bin/yacc
COPY ./bin/generateByaccs /bin/
ENTRYPOINT ["/bin/generateByaccs"]