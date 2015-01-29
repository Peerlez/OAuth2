--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: oauth; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA oauth;


ALTER SCHEMA oauth OWNER TO postgres;

--
-- Name: util; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA util;


ALTER SCHEMA util OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

SET search_path = public, pg_catalog;

--
-- Name: id; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.id OWNER TO postgres;

SET search_path = oauth, pg_catalog;

--
-- Name: authenticate(character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION authenticate(p_client_id character varying, p_password character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
BEGIN
	select id into v_id from oauth.client where client_id = p_client_id and
    client_secret = md5(p_password);
    
    if not found THEN
    	return false;
    end if;
    	return true;
END;
$$;


ALTER FUNCTION oauth.authenticate(p_client_id character varying, p_password character varying) OWNER TO postgres;

--
-- Name: check_redirecturi(character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION check_redirecturi(p_client character varying, p_redirect character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
 	v_id bigint;
BEGIN
 	select id into v_id from oauth.client where client_id = p_client and redirect_uri = p_redirect;
    IF FOUND THEN
    	return 'TRUE';
    end if;
    	return 'FALSE';
END;
$$;


ALTER FUNCTION oauth.check_redirecturi(p_client character varying, p_redirect character varying) OWNER TO postgres;

--
-- Name: check_secret(character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION check_secret(p_client_id character varying, p_password character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
  	v_id bigint;
BEGIN
 	select id into v_id from oauth.client where client_id = p_client_id and
    client_secret = md5(p_password);
    if not found then
    	return false;
    end if;
    	return true;
END;
$$;


ALTER FUNCTION oauth.check_secret(p_client_id character varying, p_password character varying) OWNER TO postgres;

--
-- Name: clientcredentials(character varying, character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION clientcredentials(p_token character varying, p_client_id character varying, p_token_type character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
    v_cur refcursor;
BEGIN
 	select nextval('public.id') into v_id;
    insert into oauth.accesstoken (id, token, client_id, type, expires, created, token_status, grant_type) 
	values (v_id, 
    		p_token,
            p_client_id,
            p_token_type, 
            now()+'3600 seconds'::interval, 
            now(),
            'valid',
            2);
    
     open v_cur for select 
     token, 
     type,
     EXTRACT(epoch FROM expires) as expires
     from oauth.accesstoken where id = v_id;
     return v_cur;
END;
$$;


ALTER FUNCTION oauth.clientcredentials(p_token character varying, p_client_id character varying, p_token_type character varying) OWNER TO postgres;

--
-- Name: create_accesstoken(character varying, character varying, character varying, bigint, bigint, bigint, character varying, bigint); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION create_accesstoken(p_token character varying, p_client_id character varying, p_token_type character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
    v_cur refcursor;
BEGIN
 	select nextval('public.id') into v_id;
    insert into oauth.accesstoken 
    (id, 
    token,
    client_id,
    type, 
    expires, 
    created, 
    token_status) 
	values (v_id, 
    		p_token,
            p_client_id,
            p_token_type, 
            now()+'3600 seconds'::interval, 
            now(),
            'valid');
    
     open v_cur for select 
     token, 
     type,
     expires
     from oauth.accesstoken where id = v_id;
     return v_cur;
END;
$$;
Just making sure that you are making the request to the right path. So you want that the server to respond from /view/view/admin not /view/admin?

ALTER FUNCTION oauth.create_accesstoken(p_token character varying, p_client_id character varying, p_token_type character varying) OWNER TO postgres;

--
-- Name: create_client(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION create_client(p_redirect_uri character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
    v_cur refcursor;
BEGIN
 	select nextval('public.id') into v_id;
    insert into oauth.client (id, client_id, redirect_uri, created) 
	values (v_id, oauth.generate_client_id(), p_redirect_uri, now());
    
     open v_cur for select 
     id, 
     client_id,
     redirect_uri,
     EXTRACT(epoch FROM created) as created
     from oauth.client where id = v_id;
     return v_cur;
END;
$$;


ALTER FUNCTION oauth.create_client(p_redirect_uri character varying) OWNER TO postgres;

--
-- Name: create_code(character varying, character varying, bigint); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION create_code(p_code character varying, p_client character varying, p_redirect bigint) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
 	v_cur refcursor;
    v_id bigint;
BEGIN
	select nextval('public.id') into v_id;

	insert into oauth.authorization_code (code, client_id, redirect_uri) 
	values (p_code, p_client, p_redirect_uri);
    
    open v_cur for select * from oauth.authorization_code where id = v_id;
    return v_cur;
END;
$$;


ALTER FUNCTION oauth.create_code(p_code character varying, p_client character varying, p_redirect bigint) OWNER TO postgres;

--
-- Name: delete_client(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION delete_client(p_client_id character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
BEGIN 
	select id into v_id from oauth.client where client_id = p_client_id;
    if not found then
    	return false;
    end if;
    delete from oauth.client where client_id = p_client_id;
    	return true;
END;
$$;


ALTER FUNCTION oauth.delete_client(p_client_id character varying) OWNER TO postgres;

--
-- Name: delete_token(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION delete_token(p_token character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
BEGIN 
	select id into v_id from oauth.accesstoken where token = p_token;
    if not found then
    	return false;
    end if;
    delete from oauth.accesstoken where token = p_token;
    return true;
END;
$$;


ALTER FUNCTION oauth.delete_token(p_token character varying) OWNER TO postgres;

--
-- Name: generate_client_id(); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION generate_client_id() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
	return md5(random()::text);
END;
$$;


ALTER FUNCTION oauth.generate_client_id() OWNER TO postgres;

--
-- Name: get_client(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION get_client(p_id character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
 	v_cur refcursor;
BEGIN
    open v_cur for select
    id,
    client_id,
    redirect_uri,
    description,
    EXTRACT(epoch FROM created) as created
    from oauth.client where client_id = p_id;
    return v_cur;
END;
$$;


ALTER FUNCTION oauth.get_client(p_id character varying) OWNER TO postgres;

--
-- Name: get_client_by_id(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION get_client_by_id(p_client character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
  	v_id bigint;
    v_cur refcursor;
BEGIN
	select id into v_id from oauth.client where client_id = p_client;
    IF NOT FOUND THEN
    	return 'FALSE';
    else
    	return 'TRUE';
    end if;
END;
$$;


ALTER FUNCTION oauth.get_client_by_id(p_client character varying) OWNER TO postgres;

--
-- Name: get_token(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION get_token(p_token character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
  v_cur refcursor;
BEGIN	
 	open v_cur for select 
    id, 
    token, 
    type, 
   	EXTRACT(epoch FROM expires) as expires,
    created, 
    client_id, 
    user_id,
    token_status,
    impersonate,
    account_id,
    grant_type,
    customer_id
    from oauth.accesstoken where token = p_token;
    	return v_cur;
END;
$$;


ALTER FUNCTION oauth.get_token(p_token character varying) OWNER TO postgres;

--
-- Name: get_user_id(character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION get_user_id(p_token character varying) RETURNS bigint
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_uid bigint;
BEGIN
	select user_id INTO v_uid FROM oauth.accesstoken where token = p_token;
    return v_uid;
END;
$$;


ALTER FUNCTION oauth.get_user_id(p_token character varying) OWNER TO postgres;

ALTER FUNCTION oauth.list_tokens(p_user bigint) OWNER TO postgres;

--
-- Name: revoke_token(character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION revoke_token(p_token character varying, p_revoked character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_cur refcursor;
BEGIN
    update oauth.accesstoken SET 
    token_status = p_revoked,
    expires = now()
    where token = p_token;
    
    open v_cur for select 
    token,
    type,
    EXTRACT(epoch FROM expires) as expires,
    created,
    client_id,
    token_status,
    from oauth.accesstoken where token = p_token;
    return v_cur;
    
END;
$$;


ALTER FUNCTION oauth.revoke_token(p_token character varying, p_revoked character varying) OWNER TO postgres;

--
-- Name: tokencleanup(); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION tokencleanup() RETURNS bigint
    LANGUAGE plpgsql
    AS $$
DECLARE
  v_count BIGINT;

BEGIN
	delete from oauth.accesstoken where expires < now();
    GET DIAGNOSTICS v_count = ROW_COUNT;
	return v_count;
END;
$$;


ALTER FUNCTION oauth.tokencleanup() OWNER TO postgres;

--
-- Name: validate_token(character varying, character varying); Type: FUNCTION; Schema: oauth; Owner: postgres
--

CREATE FUNCTION validate_token(p_token character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
DECLARE
	v_id bigint;
    v_expires timestamp;
    v_cur refcursor;
BEGIN
	select id into v_id from oauth.accesstoken where token = p_token;
    IF NOT FOUND THEN
    	return v_cur;
    end if;
   	select expires into v_expires from oauth.accesstoken where id = v_id;
    
	IF (v_expires < now()) THEN
      update oauth.accesstoken set token_status = p_expired where id = v_id;
    end if;
    
    open v_cur for select
    token,
    token_status,
   	expires,
    from oauth.accesstoken where id = v_id;
    
    return v_cur;
END;
$$;


ALTER FUNCTION oauth.validate_token(p_token character varying) OWNER TO postgres;

SET search_path = oauth, pg_catalog;

--
-- Name: accesstoken; Type: TABLE; Schema: oauth; Owner: postgres; Tablespace: 
--

CREATE TABLE accesstoken (
    id bigint NOT NULL,
    token character varying NOT NULL,
    type character varying NOT NULL,
    expires timestamp without time zone NOT NULL,
    created timestamp without time zone DEFAULT now() NOT NULL,
    client_id character varying(80),
    token_status character varying NOT NULL,
    grant_type bigint
);


ALTER TABLE oauth.accesstoken OWNER TO postgres;

--
-- Name: client; Type: TABLE; Schema: oauth; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    id bigint NOT NULL,
    client_id character varying NOT NULL,
    client_secret character varying,
    redirect_uri character varying(2000) NOT NULL,
    created timestamp without time zone DEFAULT now() NOT NULL,
    description character varying
);
ALTER TABLE ONLY client ALTER COLUMN client_secret SET STATISTICS 0;


ALTER TABLE oauth.client OWNER TO postgres;

--
-- Name: grant_type; Type: TABLE; Schema: oauth; Owner: postgres; Tablespace: 
--

CREATE TABLE grant_type (
    id bigint NOT NULL,
    type character varying NOT NULL
);


ALTER TABLE oauth.grant_type OWNER TO postgres;

SET search_path = oauth, pg_catalog;

--
-- Name: accesstoken_pkey; Type: CONSTRAINT; Schema: oauth; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT accesstoken_pkey PRIMARY KEY (id);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: oauth; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: grant_type_pkey; Type: CONSTRAINT; Schema: oauth; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grant_type
    ADD CONSTRAINT grant_type_pkey PRIMARY KEY (id);

SET search_path = oauth, pg_catalog;

--
-- Name: accesstoken_fk; Type: FK CONSTRAINT; Schema: oauth; Owner: postgres
--

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT accesstoken_fk FOREIGN KEY (grant_type) REFERENCES grant_type(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: test_fk; Type: FK CONSTRAINT; Schema: oauth; Owner: postgres
--

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT test_fk FOREIGN KEY (grant_type) REFERENCES grant_type(id);




--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

