FROM elasticsearch:2.4.1

COPY ./elasticsearch.yml /usr/share/elasticsearch/config/

RUN /usr/share/elasticsearch/bin/plugin install analysis-icu
RUN /usr/share/elasticsearch/bin/plugin install analysis-kuromoji
RUN /usr/share/elasticsearch/bin/plugin install analysis-smartcn
RUN /usr/share/elasticsearch/bin/plugin install analysis-stempel
#RUN sudo /usr/share/elasticsearch/bin/plugin install cloud-aws


