(ns angsrc.crp
  (:use [embang emit runtime]))

;; Stateful Chinese restaurant process, 
;; draws an index.

(defun crp (alpha)
  (let ((name (gensym "crp")))
    (lambda ()
      (let ((p (or (retrieve name) (CRP alpha)))
            (s (sample (produce p))))
        (store name (absorb p s))
        s))))

;; Dirichlet process, 
;; draws a value obtained from the base measure.

(defun dp (alpha H)
  (let ((name (gensym "dp")))
    (lambda ()
      (let ((p (or (retrieve name) (CRP alpha)))
            (s (sample (produce p))))
        (store name (absorb p s))
        ((mem (lambda (s) (H))) s)))))
