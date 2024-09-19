CREATE OR REPLACE FUNCTION fnAuditory() RETURNS trigger AS $$
	DECLARE
		szMessage TEXT;

	BEGIN 
		IF tg_table_name LIKE '%users%' THEN 
			szMessage := FORMAT('User %s has been created', new.username);
			INSERT INTO postgres.auditory (message) VALUES (szMessage);
		ELSE 
			szMessage := FORMAT('Token %s has ben created', new.token);
			INSERT INTO postgres.auditory (message) VALUES (szMessage);
		END IF;
		
		RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER onTriggerSaveUser
AFTER INSERT ON postgres.users
FOR EACH ROW
EXECUTE FUNCTION postgres.fnAuditory();

CREATE OR REPLACE TRIGGER onTriggerSaveToken
AFTER INSERT ON postgres.email_token
FOR EACH ROW
EXECUTE FUNCTION postgres.fnAuditory();